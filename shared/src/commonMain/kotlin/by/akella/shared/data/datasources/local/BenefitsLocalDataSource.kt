package by.akella.shared.data.datasources.local

import by.akella.shared.data.datasources.BenefitsDataSource
import by.akella.shared.domain.models.BenefitModel
import by.akella.sqldelight.benefits.Benefits
import kotlinx.coroutines.flow.Flow

class BenefitsLocalDataSource(
    private val benefits: Benefits
) : BenefitsDataSource {

    override suspend fun saveBenefits(benefits: List<BenefitModel>) {
        TODO("Not yet implemented")
    }

    override fun getBenefitList(force: Boolean): Flow<List<BenefitModel>> {
        TODO("Not yet implemented")
    }

    override fun getBenefit(id: String): Flow<BenefitModel> {
        TODO("Not yet implemented")
    }
}