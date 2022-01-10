package com.app.coroutinesmvp.data

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("results") val result: List<MovieListResult>
) {
    data class MovieListResult(
        @SerializedName("backdrop_path") val backDropPath: String?,
        @SerializedName("original_title") val originalTitle: String?,
        @SerializedName("overview") val overview: String?,
        @SerializedName("release_date") val releaseDate: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("vote_average") val voteAverage: Double?
    )
}
