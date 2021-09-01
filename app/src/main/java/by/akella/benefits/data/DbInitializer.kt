package by.akella.benefits.data

import android.content.Context
import androidx.core.content.edit
import by.akella.benefits.domain.BenefitsRepository

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

        private const val PREFS_NAME = "by.akella.benefits.prefs.name"
        private const val FIRST_LAUNCH_KEY = "by.akella.benefits.launch.key"
    }
}