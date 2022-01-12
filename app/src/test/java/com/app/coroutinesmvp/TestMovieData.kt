package com.app.coroutinesmvp

import com.app.coroutinesmvp.data.MovieListResponse

object TestMovieData {
    fun getMovieData(): MovieListResponse {
        return MovieListResponse(
            listOf(
                MovieListResponse.MovieListResult(
                    backDropPath = "",
                    title = "Spider man",
                    releaseDate = "26 oct 2019",
                    voteAverage = 4.5,
                    originalTitle = "Spider man: The man",
                    overview = "Nice movie"
                )
            )
        )
    }
}
