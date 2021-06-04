package com.example.benefits.ui

import android.app.Application
import android.database.DatabaseUtils
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.benefits.data.BenefitsRepositoryImpl
import com.example.benefits.data.DbInitializer
import com.example.benefits.data.JsonDataExtractor
import com.example.benefits.data.db.BenefitsDb
import com.example.benefits.domain.BenefitsRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class App : Application() {

    val db by lazy {
        Room.databaseBuilder(this, BenefitsDb::class.java, "benefitsDb").build()
    }

    val repository: BenefitsRepository by lazy {
        BenefitsRepositoryImpl(db.benefitsDao)
    }

    val dbInitializer: DbInitializer by lazy {
        DbInitializer(this, JsonDataExtractor(), repository)
    }

    override fun onCreate() {
        super.onCreate()

        GlobalScope.launch {
            dbInitializer.init()
        }
    }
}