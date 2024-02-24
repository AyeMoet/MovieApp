package com.am.themovieapp.network.responses

import com.am.themovieapp.data.vos.ActorVO
import com.am.themovieapp.data.vos.DateVO
import com.am.themovieapp.data.vos.GenreVO
import com.am.themovieapp.data.vos.MovieVO
import com.google.gson.annotations.SerializedName

data class GetActorsResponse (
    @SerializedName("results")
    val results: List<ActorVO>?
)