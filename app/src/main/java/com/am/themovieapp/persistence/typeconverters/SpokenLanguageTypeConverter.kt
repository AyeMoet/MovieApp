package com.am.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.am.themovieapp.data.vos.SpokenLanguagesVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SpokenLanguageTypeConverter {
    //custom object to string(return type must always string type)
    @TypeConverter
    fun toString(spokenLanguage: List<SpokenLanguagesVO>?): String {
        return Gson().toJson(spokenLanguage)
    }

    //string to custom object
    @TypeConverter
    fun toSpokenLanguageList(spokenLanguageListJsonStr: String): List<SpokenLanguagesVO>? {
        val spokenLanguageListType = object : TypeToken<List<SpokenLanguagesVO>?>() {}.type
        return Gson().fromJson(spokenLanguageListJsonStr,spokenLanguageListType)
    }
}