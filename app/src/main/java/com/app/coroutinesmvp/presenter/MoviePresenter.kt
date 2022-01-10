package com.app.coroutinesmvp.presenter

import com.app.coroutinesmvp.MovieContract
import com.app.coroutinesmvp.deps.MovieActivityScope
import com.app.coroutinesmvp.model.MovieModel
import kotlinx.coroutines.CoroutineScope
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
}
