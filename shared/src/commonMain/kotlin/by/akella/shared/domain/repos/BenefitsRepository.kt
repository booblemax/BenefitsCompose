package by.akella.shared.domain.repos

import by.akella.shared.domain.models.BenefitModel
import kotlinx.coroutines.flow.Flow

interface BenefitsRepository {

    fun saveBenefits(benefits: List<BenefitModel>)

    fun getBenefitList(force: Boolean): Flow<List<BenefitModel>>

    fun getBenefit(id: String): Flow<BenefitModel>
}