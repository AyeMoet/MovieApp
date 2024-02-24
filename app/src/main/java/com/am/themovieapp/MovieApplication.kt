package com.am.themovieapp

import android.app.Application
import com.am.themovieapp.data.models.MovieModelImpl

class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        MovieModelImpl.initDatabase(applicationContext)
    }
}