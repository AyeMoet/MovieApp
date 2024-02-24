package com.am.themovieapp.data.models

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import com.am.themovieapp.data.vos.*
import com.am.themovieapp.persistence.MovieDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object MovieModelImpl : BaseModel(),MovieModel {
    @SuppressLint("CheckResult")
    override fun getNowPlayingMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {
        //network
        mTheMovieApi.getNowPlayingMovie(page =1 )
            .subscribeOn(Schedulers.io())//background thread
            .observeOn(AndroidSchedulers.mainThread())
            //next event
            .subscribe({
                it.results?.forEach{ movie ->movie.type = NOW_PLAYING
                }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
            },
                //onError
                {
                onFailure(it.localizedMessage ?: "")
            },
            )
        return mMovieDatabase?.movieDao()?.getMovieByType(type = NOW_PLAYING)

    }

    @SuppressLint("CheckResult")
    override fun getPopularMovies( onFailure: (String) -> Unit)
            : LiveData<List<MovieVO>>?{
        //network
        mTheMovieApi.getPopularRMovie(page =1 )
            .subscribeOn(Schedulers.io())//background thread
            .observeOn(AndroidSchedulers.mainThread())
            //next event
            .subscribe({
                it.results?.forEach{ movie ->movie.type = POPULAR
                }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
            },
                //onError
                {
                    onFailure(it.localizedMessage ?: "")
                },
            )

        return mMovieDatabase?.movieDao()?.getMovieByType(type = POPULAR)
    }

    @SuppressLint("CheckResult")
    override fun getTopRatedMovies(
        onFailure: (String) -> Unit
    ): LiveData<List<MovieVO>>? {
        //network
        mTheMovieApi.getTopRatedMovie(page =1 )
            .subscribeOn(Schedulers.io())//background thread
            .observeOn(AndroidSchedulers.mainThread())
            //next event
            .subscribe({
                it.results?.forEach{ movie ->movie.type = TOP_RATED
                }
                mMovieDatabase?.movieDao()?.insertMovies(it.results ?: listOf())
            },
                //onError
                {
                    onFailure(it.localizedMessage ?: "")
                },
            )
        return mMovieDatabase?.movieDao()?.getMovieByType(type = TOP_RATED)
    }

    @SuppressLint("CheckResult")
    override fun getGenres(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        mTheMovieApi.getGenres()
            .subscribeOn(Schedulers.io())//background thread
            .observeOn(AndroidSchedulers.mainThread())
            //next event
            .subscribe({
                onSuccess(it.genres ?: listOf())
            },
                //onError
                {
                    onFailure(it.localizedMessage ?: "")
                },
            )
    }

    @SuppressLint("CheckResult")
    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi.getMoviesByGenre(genreId = genreId,)
            .subscribeOn(Schedulers.io())//background thread
            .observeOn(AndroidSchedulers.mainThread())
            //next event
            .subscribe({
                onSuccess(it.results ?: listOf())
            },
                //onError
                {
                    onFailure(it.localizedMessage ?: "")
                },
            )
    }

    @SuppressLint("CheckResult")
    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        mTheMovieApi.getActors()
            .subscribeOn(Schedulers.io())//background thread
            .observeOn(AndroidSchedulers.mainThread())
            //next event
            .subscribe({
                onSuccess(it.results ?: listOf())
            },
                //onError
                {
                    onFailure(it.localizedMessage ?: "")
                },
            )
    }

    @SuppressLint("CheckResult")
    override fun getMovieDetails(
        movieId: String,
        onFailure: (String) -> Unit
    ): LiveData<MovieVO>? {

        //network
        mTheMovieApi.getMovieDetails(movieId = movieId)
            .subscribeOn(Schedulers.io())//background thread
            .observeOn(AndroidSchedulers.mainThread())
            //next event
            .subscribe({
                var mMovieFromDatabase = mMovieDatabase?.movieDao()?.getMovieByIdOneTime(movieId = movieId.toInt())
                it.type = mMovieFromDatabase?.type
                mMovieDatabase?.movieDao()?.insertSingleMovies(it)
            }, {
                    onFailure(it.localizedMessage ?: "")
                }
            )

        return mMovieDatabase?.movieDao()?.getMovieById(movieId = movieId.toInt())
    }

    @SuppressLint("CheckResult")
    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi.getCreditsByMovie(movieId = movieId)
            .subscribeOn(Schedulers.io())//background thread
            .observeOn(AndroidSchedulers.mainThread())
            //next event
            .subscribe({
                onSuccess(Pair(it.cast?: listOf(),it.crew ?: listOf()))
            }, {
                onFailure(it.localizedMessage ?: "")
            }
            )
    }

    override fun searchMovie(query: String): Observable<List<MovieVO>>? {
        return mTheMovieApi
            .searchMovie(query = query)
            .map { it.results?: listOf() }
            .onErrorResumeNext { Observable.just(listOf()) }
            .subscribeOn(Schedulers.io())
    }
}