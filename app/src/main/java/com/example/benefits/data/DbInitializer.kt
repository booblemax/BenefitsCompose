package com.example.benefits.data

import android.content.Context
import androidx.core.content.edit
import com.example.benefits.domain.BenefitsRepository

class DbInitializer(
    private val context: Context,
    private val extractor: JsonDataExtractor,
    private val repository: BenefitsRepository
) {

    suspend fun init() {
        with(context) {
            val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val isFirstLaunch = sharedPreferences.getBoolean(FIRST_LAUNCH_KEY, false)

            if (!isFirstLaunch) {
                val benefits = extractor.loadDataFromJson(context)
                repository.saveBenefits(benefits)
                sharedPreferences.edit {
                    putBoolean(FIRST_LAUNCH_KEY, true)
                }
            }
        }
    }

    companion object  {

        private const val PREFS_NAME = "com.example.benefits.prefs.name"
        private const val FIRST_LAUNCH_KEY = "com.example.benefits.launch.key"
    }
}