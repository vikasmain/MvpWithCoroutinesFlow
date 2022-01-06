package com.app.coroutinesmvp.model

import com.app.coroutinesmvp.api.MovieApi
import com.app.coroutinesmvp.data.MovieListResponse
import com.app.coroutinesmvp.deps.MovieActivityScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@MovieActivityScope
class MovieModel @Inject constructor(
    private val movieApi: MovieApi
) {
    fun callMoviesApi(): Flow<MovieListResponse> {
        return flow {
            val carbonOffsetResponse = movieApi.getMoviesList()
            emit(carbonOffsetResponse)
        }.flowOn(Dispatchers.IO)
    }
}
