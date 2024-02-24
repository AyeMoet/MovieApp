package com.am.themovieapp.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.am.themovieapp.data.models.MovieModelImpl
import com.am.themovieapp.data.vos.ActorVO
import com.am.themovieapp.data.vos.GenreVO
import com.am.themovieapp.data.vos.MovieVO

class MovieDetailsViewModel: ViewModel() {
    //Model
    private val mMovieModel = MovieModelImpl

    //LiveData
    var movieDetailLiveData: LiveData<MovieVO>? = null
    var castLiveData = MutableLiveData<List<ActorVO>>()
    var crewMovieLiveData = MutableLiveData<List<ActorVO>>()
    val mErrorLiveData = MutableLiveData<String>()

    fun getInitData(movieId:Int) {
        Log.d("test_data","movie id $movieId")
        movieDetailLiveData =
            mMovieModel.getMovieDetails(movieId = movieId.toString()) {mErrorLiveData.postValue(it)}
        mMovieModel.getCreditsByMovie(
            movieId.toString(),
            onSuccess = {
                castLiveData.postValue(it.first ?: listOf())
                crewMovieLiveData.postValue(it.second ?: listOf())
            },
            onFailure = {
                mErrorLiveData.postValue(it)
            }
        )
    }
}