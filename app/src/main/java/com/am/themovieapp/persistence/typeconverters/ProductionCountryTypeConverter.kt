package com.am.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.am.themovieapp.data.vos.ProductionCountriesVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCountryTypeConverter {
    //custom object to string(return type must always string type)
    @TypeConverter
    fun toString(productionCountry: List<ProductionCountriesVO>?): String {
        return Gson().toJson(productionCountry)
    }

    //string to custom object
    @TypeConverter
    fun toProductionCountryList(productionCountryJsonStr: String): List<ProductionCountriesVO>? {
        val productionCountryListType = object : TypeToken<List<ProductionCountriesVO>?>() {}.type
        return Gson().fromJson(productionCountryJsonStr,productionCountryListType)
    }
}