package com.am.themovieapp.network.responses

import com.am.themovieapp.data.vos.DateVO
import com.am.themovieapp.data.vos.MovieVO
import com.google.gson.annotations.SerializedName

data class MovieListResponse (
    @SerializedName("dates")
    val dates: DateVO?,

    @SerializedName("page")
    val page: Int?,

    @SerializedName("results")
    val results: List<MovieVO>?,

    @SerializedName("total_pages")
    val totalPages: Int?,

    @SerializedName("total_results")
    val totalResults: Int?,
)