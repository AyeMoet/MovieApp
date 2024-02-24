package com.am.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdsTypeConverter {
    //custom object to string(return type must always string type)
    @TypeConverter
    fun toString(genreList: List<Int>?): String {
        return Gson().toJson(genreList)
    }

    //string to custom object
    @TypeConverter
    fun toGenreIds(genreListJSONString: String): List<Int>? {
        val genreIdsListType = object : TypeToken<List<Int>?>() {}.type
        return Gson().fromJson(genreListJSONString,genreIdsListType)
    }
}