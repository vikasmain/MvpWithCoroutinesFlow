package com.app.coroutinesmvp

import com.app.coroutinesmvp.model.MovieModel
import com.app.coroutinesmvp.presenter.MoviePresenter
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.plus
import org.junit.Before
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
class MoviePresenterTest {
    private val view = mock<MovieContract.MovieView>()
    private val scope = MainScope() + Dispatchers.Unconfined
    private val movieModel = mock<MovieModel>()
    private val moviePresenter =
        MoviePresenter(movieModel = movieModel, scope = scope, movieView = view)

    @DisplayName("when movie list api succeeds then show movie list in recyclerview")
    @Test
    fun testApiSucceeds() {
        val testData = flow {
            emit(TestMovieData.getMovieData())
        }
        whenever(movieModel.callMoviesApi()).thenReturn(testData)
        moviePresenter.callMoviesApi()
        verify(view).showLoadingView()
        verify(view).showMoviesList(TestMovieData.getMovieData())
        verify(view).hideLoadingView()

    }

    @DisplayName("when movie list api failed then show error view")
    @Test
    fun testApiFailed() {
        moviePresenter.callMoviesApi()
        verify(view).showLoadingView()
        verify(view).showErrorView()
        verify(view).hideLoadingView()
    }

    @DisplayName("test on click listener for movie item")
    @Test
    fun testOnclickListener() {
        MovieStateFlow.onClickStateFlow.value = "Spider man"
        moviePresenter.observeOnClickStateFlow()
        verify(view).openSingleItemView("Spider man")
    }
}
