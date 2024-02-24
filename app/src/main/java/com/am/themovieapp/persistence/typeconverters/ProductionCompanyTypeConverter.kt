package com.am.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.am.themovieapp.data.vos.ProductionCompaniesVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductionCompanyTypeConverter {
    //custom object to string(return type must always string type)
    @TypeConverter
    fun toString(productionCompany: List<ProductionCompaniesVO>?): String {
        return Gson().toJson(productionCompany)
    }

    //string to custom object
    @TypeConverter
    fun toProductionCompanyList(ProductionCompanyJsonStr: String): List<ProductionCompaniesVO>? {
        val productionCompanyListType = object : TypeToken<List<ProductionCompaniesVO>?>() {}.type
        return Gson().fromJson(ProductionCompanyJsonStr,productionCompanyListType)
    }
}