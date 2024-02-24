package com.am.themovieapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.am.themovieapp.data.models.MovieModelImpl
import com.am.themovieapp.data.vos.ActorVO
import com.am.themovieapp.data.vos.GenreVO
import com.am.themovieapp.data.vos.MovieVO

class MainViewModel: ViewModel() {
    //Model
    private val mMovieModel = MovieModelImpl

    //LiveData
    var nowPlayingMovieLiveData: LiveData<List<MovieVO>>? = null //Persistence layer ကနေ reactive နဲ့စောင့်ကြည့်တာမျိုး
    var popularMovieLiveData: LiveData<List<MovieVO>>? = null
    var topRatedMovieLiveData: LiveData<List<MovieVO>>? = null
    var genresLiveData = MutableLiveData<List<GenreVO>>()//direct value from network layer
    var moviesByGenreLiveData = MutableLiveData<List<MovieVO>>()
    var actorsLiveData = MutableLiveData<List<ActorVO>>()
    val onErrorLiveData = MutableLiveData<String>()

    fun getInitData() {
        nowPlayingMovieLiveData = mMovieModel.getNowPlayingMovies { onErrorLiveData.postValue(it) }
        popularMovieLiveData = mMovieModel.getPopularMovies { onErrorLiveData.postValue(it) }
        topRatedMovieLiveData = mMovieModel.getTopRatedMovies { onErrorLiveData.postValue(it) }
        mMovieModel.getGenres(
            onSuccess = {
                genresLiveData.postValue(it)
                getMovieByGenre(0)
            },
            onFailure = {
                onErrorLiveData.postValue(it)
            }
        )

        mMovieModel.getActors(
            onSuccess = {
                actorsLiveData.postValue(it)
            }, onFailure = {
                onErrorLiveData.postValue(it)
            }
        )
    }

    fun getMovieByGenre(genrePosition: Int) {
        genresLiveData.value?.getOrNull(genrePosition)?.id?.let {
            mMovieModel.getMoviesByGenre(it.toString(),
            onSuccess = {
                        moviesByGenreLiveData.postValue(it)
            }, onFailure = {
                onErrorLiveData.postValue(it)
                })
        }
    }
}