package by.akella.benefits.domain

import by.akella.benefits.domain.models.BenefitModel
import kotlinx.coroutines.flow.Flow

interface BenefitsRepository {

    fun saveBenefits(benefits: List<BenefitModel>)

    fun getBenefitList(force: Boolean): Flow<List<BenefitModel>>

    fun getBenefit(id: String): Flow<BenefitModel>
}