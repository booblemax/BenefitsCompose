package com.example.benefits.data

import com.example.benefits.data.dao.BenefitsDao
import com.example.benefits.domain.BenefitsRepository
import com.example.benefits.domain.models.BenefitModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BenefitsRepositoryImpl(
    private val benefitsDao: BenefitsDao
) : BenefitsRepository {

    private val dataExtractor = JsonDataExtractor()

    private val cachedModels: MutableList<BenefitModel> = mutableListOf()

    override fun saveBenefits(benefits: List<BenefitModel>) {
        benefitsDao.insert(benefits.toTypedArra)
    }

    override fun getBenefitList(): Flow<List<BenefitModel>> =
        flow {
        if (cachedModels.isEmpty()) {
            val benefitDtos = dataExtractor.loadDataFromJson()
            benefitDtos.map { it.toModel() }.toCollection(cachedModels)
        }
        delay(1000L)
        emit(cachedModels)
    }

    override fun getBenefit(id: String): Flow<BenefitModel> = flow {
        delay(500L)
        emit(cachedModels.first { it.id == id })
    }
}