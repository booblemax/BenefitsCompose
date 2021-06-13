package com.example.benefits.data

import com.example.benefits.data.dao.BenefitsDao
import com.example.benefits.domain.BenefitsRepository
import com.example.benefits.domain.models.BenefitModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class BenefitsRepositoryImpl(
    private val benefitsDao: BenefitsDao
) : BenefitsRepository {

    override fun saveBenefits(benefits: List<BenefitModel>) {
        benefitsDao.insert(*benefits.map { it.toEntity() }.toTypedArray())
    }

    override fun getBenefitList(): Flow<List<BenefitModel>> = flow {
        benefitsDao.getAll().map { models -> models.toModel() }.run { emit(this) }
    }

    override fun getBenefit(id: String): Flow<BenefitModel> = flow {
        benefitsDao.getById(id).toModel().run { emit(this) }
    }
}