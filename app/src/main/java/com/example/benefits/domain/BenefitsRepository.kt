package com.example.benefits.domain

import com.example.benefits.domain.models.BenefitModel
import kotlinx.coroutines.flow.Flow

interface BenefitsRepository {

    fun getBenefitList(): Flow<List<BenefitModel>>

    fun getBenefit(id: String): Flow<BenefitModel>
}