package com.app.coroutinesmvp.api

import com.app.coroutinesmvp.data.MovieListResponse

interface MovieApi {
    suspend fun getMoviesList(): MovieListResponse
}