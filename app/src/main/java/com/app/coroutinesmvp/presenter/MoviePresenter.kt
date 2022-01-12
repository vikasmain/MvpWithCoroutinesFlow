package com.app.coroutinesmvp.presenter

import android.util.Log
import com.app.coroutinesmvp.MovieContract
import com.app.coroutinesmvp.MovieStateFlow
import com.app.coroutinesmvp.deps.MovieActivityScope
import com.app.coroutinesmvp.model.MovieModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@MovieActivityScope
class MoviePresenter @Inject constructor(
    private val movieView: MovieContract.MovieView,
    private val scope: CoroutineScope,
    private val movieModel: MovieModel
) {
    fun onAttach() {
        callMoviesApi()
        observeOnClickStateFlow()
    }

    internal fun callMoviesApi() {
        movieModel.callMoviesApi()
            .onStart {
                movieView.showLoadingView()
            }
            .catch {
                movieView.showErrorView()
            }
            .onEach {
                movieView.showMoviesList(it)
            }
            .onCompletion {
                movieView.hideLoadingView()
            }
            .launchIn(scope)
    }

    internal fun observeOnClickStateFlow() {
        MovieStateFlow.onClickStateFlow.asStateFlow()
            .onEach {
                it?.let {
                    movieView.openSingleItemView(it)
                }
            }
            .catch {
                Log.e("MovieActivity", "Error while observing stateflow$it")
            }.launchIn(scope)
    }
}
