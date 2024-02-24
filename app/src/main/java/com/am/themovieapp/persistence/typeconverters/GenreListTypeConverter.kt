package com.am.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.am.themovieapp.data.vos.GenreVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreListTypeConverter {
    //custom object to string(return type must always string type)
    @TypeConverter
    fun toString(genreList: List<GenreVO>?): String {
        return Gson().toJson(genreList)
    }

    //string to custom object
    @TypeConverter
    fun toGenreList(genreListJSONString: String): List<GenreVO>? {
        val genreListType = object : TypeToken<List<GenreVO>?>() {}.type
        return Gson().fromJson(genreListJSONString ,genreListType)
    }
}