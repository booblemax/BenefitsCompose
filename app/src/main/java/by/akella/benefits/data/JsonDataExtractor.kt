package by.akella.benefits.data

import android.content.Context
import by.akella.benefits.data.datasource.local.BenefitEntity
import by.akella.benefits.data.datasource.local.toModel
import by.akella.benefits.domain.models.BenefitModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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