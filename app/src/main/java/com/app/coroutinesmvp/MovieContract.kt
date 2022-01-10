package com.app.coroutinesmvp

import com.app.coroutinesmvp.data.MovieListResponse

interface MovieContract {
    interface MovieView {
        fun showMoviesList(movieListResponse: MovieListResponse)
        fun showErrorView()
        fun showLoadingView()
        fun hideLoadingView()
        fun openSingleItemView(title: String)
    }
}
