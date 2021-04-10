package com.example.benefits.domain

import com.example.benefits.domain.models.BenefitModel
import kotlinx.coroutines.flow.Flow

interface BenefitsRepository {

    fun getBenefits(): Flow<List<BenefitModel>>
}