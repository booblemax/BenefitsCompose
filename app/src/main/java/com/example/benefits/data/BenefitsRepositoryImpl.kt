package com.example.benefits.data

import com.example.benefits.domain.BenefitsRepository
import com.example.benefits.domain.models.BenefitModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BenefitsRepositoryImpl : BenefitsRepository {

    private val dataExtractor = JsonDataExtractor()

    override fun getBenefits(): Flow<List<BenefitModel>> = flow {
        val benefitDtos = dataExtractor.loadDataFromJson()
        val models = benefitDtos.map { it.toModel() }
        emit(models)
    }
}