package com.example.benefits.data

import android.content.Context
import com.example.benefits.domain.models.BenefitModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class JsonDataExtractor {

    suspend fun loadDataFromJson(context: Context): List<BenefitModel> =
        withContext(Dispatchers.IO) {
            val gson = Gson()
            val entities: List<BenefitEntity> = context.assets.open("db-seed.json").use {
                gson.fromJson(it.reader(), object : TypeToken<List<BenefitEntity>>() {}.type)
            }
            entities.map { it.toModel() }
        }
}