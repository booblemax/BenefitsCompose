package com.example.benefits.di

import androidx.room.Room
import com.example.benefits.data.DbInitializer
import com.example.benefits.data.JsonDataExtractor
import com.example.benefits.data.db.BenefitsDb
import org.koin.dsl.module

val dbModule = module {

    single<BenefitsDb> {
        Room.databaseBuilder(get(), BenefitsDb::class.java, "benefitsDb").build()
    }

    factory { get<BenefitsDb>().benefitsDao }

    factory { DbInitializer(get(), get(), get()) }

    factory { JsonDataExtractor() }
}