package com.example.benefits.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.benefits.data.BenefitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BenefitsDao {

    @Insert
    fun insert(vararg models: BenefitEntity)

    @Delete
    fun delete(vararg models: BenefitEntity)

    @Query("SELECT * FROM benefits")
    fun getAll(): Flow<List<BenefitEntity>>

    @Query("SELECT * FROM benefits WHERE id = :id")
    fun getById(id: String): Flow<BenefitEntity>
}