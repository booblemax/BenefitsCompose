package by.akella.benefits.data.datasource.local.dao

import androidx.room.*
import by.akella.benefits.data.datasource.local.BenefitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BenefitsDao : BaseDao<BenefitEntity> {

    @Query("SELECT * FROM benefits")
    suspend fun getAll(): List<BenefitEntity>

    @Query("SELECT * FROM benefits WHERE id = :id")
    suspend fun getById(id: String): BenefitEntity
}