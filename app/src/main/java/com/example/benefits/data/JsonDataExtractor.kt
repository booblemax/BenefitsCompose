package com.example.benefits.data

import android.content.Context
import com.example.benefits.domain.models.AddressModel
import com.example.benefits.domain.models.BenefitModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JsonDataExtractor {

    suspend fun loadDataFromJson(context: Context): List<BenefitModel> =
        withContext(Dispatchers.IO) {
            val gson = Gson()
            context.assets.open("db-seed.json").use {
                gson.fromJson(it.reader(), object : TypeToken<List<BenefitModel>>() {}.type)
            }
        }
}