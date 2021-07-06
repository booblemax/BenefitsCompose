package com.example.benefits.ui

import android.app.Application
import com.example.benefits.data.BenefitsRepositoryImpl
import com.example.benefits.data.DbInitializer
import com.example.benefits.data.JsonDataExtractor
import com.example.benefits.di.dbModule
import com.example.benefits.di.domainModule
import com.example.benefits.di.vmModule
import com.example.benefits.domain.BenefitsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.Koin
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    private val dbInitializer by inject<DbInitializer>()

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(dbModule, domainModule, vmModule)
        }

        GlobalScope.launch {
            dbInitializer.init()
        }
    }
}