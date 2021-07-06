package com.example.benefits.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.benefits.data.BenefitEntity
import com.example.benefits.data.dao.BenefitsDao
import com.example.benefits.data.db.BenefitsDb.Companion.DB_VERSION

@Database(entities = [BenefitEntity::class], version = DB_VERSION)
abstract class BenefitsDb : RoomDatabase() {

    abstract val benefitsDao: BenefitsDao

    companion object {

        const val DB_VERSION = 1
    }
}