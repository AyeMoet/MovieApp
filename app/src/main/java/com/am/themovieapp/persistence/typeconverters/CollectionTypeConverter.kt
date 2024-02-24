package com.am.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.am.themovieapp.data.vos.CollectionVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CollectionTypeConverter {
    //custom object to string(return type must always string type)
    @TypeConverter
    fun toString(collection: CollectionVO?): String {
        return Gson().toJson(collection)
    }

    //string to custom object
    @TypeConverter
    fun toCollectionVo(collectionListJsonStr: String): CollectionVO? {
        val collectionVOType = object : TypeToken<CollectionVO?>() {}.type
        return Gson().fromJson(collectionListJsonStr,collectionVOType)
    }
}