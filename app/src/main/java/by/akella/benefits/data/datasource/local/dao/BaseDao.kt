package by.akella.benefits.data.datasource.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import by.akella.benefits.data.datasource.local.BenefitEntity

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg models: T)

    @Update
    suspend fun update(vararg models: T)

    @Delete
    suspend fun delete(vararg models: T)}