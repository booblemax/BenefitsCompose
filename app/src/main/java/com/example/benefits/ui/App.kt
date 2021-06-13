package com.example.benefits.ui

import android.app.Application
import android.database.DatabaseUtils
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.benefits.data.BenefitsRepositoryImpl
import com.example.benefits.data.DbInitializer
import com.example.benefits.data.JsonDataExtractor
import com.example.benefits.data.db.BenefitsDb
import com.example.benefits.di.DbManager
import com.example.benefits.domain.BenefitsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

class App : Application() {

    val repository: BenefitsRepository by lazy {
        BenefitsRepositoryImpl(DbManager.benefitsDao)
    }

    val dbInitializer: DbInitializer by lazy {
        DbInitializer(this, JsonDataExtractor(), repository)
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        DbManager.init(this)

        GlobalScope.launch {
            dbInitializer.init()
        }
    }
}