package com.app.coroutinesmvp.api

import com.app.coroutinesmvp.data.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/3/discover/movie")
    suspend fun getMoviesList(@Query("api_key") apiKey: String): MovieListResponse
}
