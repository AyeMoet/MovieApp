package com.am.themovieapp.network

import com.am.themovieapp.BuildConfig.MOVIE_API_KEY
import com.am.themovieapp.data.vos.MovieVO
import com.am.themovieapp.network.EndPoints.API_CREDITS_BY_MOVIE
import com.am.themovieapp.network.EndPoints.API_GET_ACTORS
import com.am.themovieapp.network.EndPoints.API_GET_GENRE
import com.am.themovieapp.network.EndPoints.API_GET_MOVIES_BY_GENRE
import com.am.themovieapp.network.EndPoints.API_GET_NOW_PLAYING
import com.am.themovieapp.network.EndPoints.API_GET_POPULAR_MOVIES
import com.am.themovieapp.network.EndPoints.API_GET_TOP_RATED_MOVIES
import com.am.themovieapp.network.EndPoints.API_MOVIES_DETAILS
import com.am.themovieapp.network.EndPoints.API_SEARCH_MOVIE
import com.am.themovieapp.network.EndPoints.PARAM_API_KEY
import com.am.themovieapp.network.EndPoints.PARAM_GENRE_ID
import com.am.themovieapp.network.EndPoints.PARAM_PAGE
import com.am.themovieapp.network.EndPoints.PARAM_QUERY
import com.am.themovieapp.network.responses.GetActorsResponse
import com.am.themovieapp.network.responses.GetCreditsByMovieResponse
import com.am.themovieapp.network.responses.GetGenresResponse
import com.am.themovieapp.network.responses.MovieListResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi {
    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovie(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int = 1,
    ): Observable<MovieListResponse>

    @GET(API_GET_POPULAR_MOVIES)
    fun getPopularRMovie(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int = 1,
    ): Observable<MovieListResponse>

    @GET(API_GET_TOP_RATED_MOVIES)
    fun getTopRatedMovie(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int = 1,
    ): Observable<MovieListResponse>

    @GET(API_GET_GENRE)
    fun getGenres(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY
    ): Observable<GetGenresResponse>

    @GET(API_GET_MOVIES_BY_GENRE)
    fun getMoviesByGenre(
        @Query(PARAM_GENRE_ID) genreId: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
    ): Observable<MovieListResponse>

    @GET(API_GET_ACTORS)
    fun getActors(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int = 1,
    ): Observable<GetActorsResponse>

    @GET("$API_MOVIES_DETAILS/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int = 1,
    ): Observable<MovieVO>

    @GET("$API_CREDITS_BY_MOVIE/{movie_id}/credits")
    fun getCreditsByMovie(
        @Path("movie_id") movieId: String,
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int = 1,
    ): Observable<GetCreditsByMovieResponse>

    @GET(API_SEARCH_MOVIE)
    fun searchMovie(
        @Query(PARAM_API_KEY) apiKey: String = MOVIE_API_KEY,
        @Query(PARAM_QUERY) query:String ,
    ): Observable<MovieListResponse>
}