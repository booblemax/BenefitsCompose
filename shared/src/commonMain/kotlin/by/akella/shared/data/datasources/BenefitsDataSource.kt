package by.akella.shared.data.datasources

import by.akella.shared.domain.models.BenefitModel
import kotlinx.coroutines.flow.Flow

interface BenefitsDataSource {

    suspend fun saveBenefits(benefits: List<BenefitModel>)

    fun getBenefitList(force: Boolean): Flow<List<BenefitModel>>

    fun getBenefit(id: String): Flow<BenefitModel>
}