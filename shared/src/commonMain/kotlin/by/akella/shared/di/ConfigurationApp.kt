package by.akella.shared.di

import by.akella.shared.data.datasources.BenefitsDataSource
import by.akella.shared.data.datasources.remote.BenefitsRemoteDataSource

class ConfigurationApp {
    val appContainer: DIManager by lazy { DIManager() }

    init {
        setup()
    }

    fun setup() {
        //register components
        appContainer.addToScope(BenefitsDataSource::class) {
            BenefitsRemoteDataSource()
        }
    }
}