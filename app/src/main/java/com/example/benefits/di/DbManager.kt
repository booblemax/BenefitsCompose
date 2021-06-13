package com.example.benefits.di

import android.content.Context
import androidx.room.Room
import com.example.benefits.data.dao.BenefitsDao
import com.example.benefits.data.db.BenefitsDb

object DbManager {

    val benefitsDao: BenefitsDao get() = db.benefitsDao

    private lateinit var db: BenefitsDb

    fun init(context: Context) {
        db = Room.databaseBuilder(context, BenefitsDb::class.java, "benefitsDb").build()
    }
}