package com.am.themovieapp.data.models

import android.content.Context
import com.am.themovieapp.BuildConfig
import com.am.themovieapp.network.TheMovieApi
import com.am.themovieapp.persistence.MovieDatabase
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseModel {
    var mTheMovieApi: TheMovieApi
    // database
    var mMovieDatabase: MovieDatabase? = null

    init {
        val mOkHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS) //connect Url
            .readTimeout(15, TimeUnit.SECONDS) //read response
            .writeTimeout(15, TimeUnit.SECONDS) // send request
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

        mTheMovieApi = retrofit.create(TheMovieApi::class.java)
    }


    fun initDatabase(context: Context){
        mMovieDatabase = MovieDatabase.getDBInstance(context)
    }
}