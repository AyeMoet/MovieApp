package com.am.themovieapp.network.responses

import com.am.themovieapp.data.vos.ActorVO
import com.am.themovieapp.data.vos.DateVO
import com.am.themovieapp.data.vos.GenreVO
import com.am.themovieapp.data.vos.MovieVO
import com.google.gson.annotations.SerializedName

data class GetCreditsByMovieResponse (
    @SerializedName("cast")
    val cast: List<ActorVO>?,
    @SerializedName("crew")
    val crew: List<ActorVO>?
)