package by.akella.shared.data.datasources.remote

import by.akella.shared.data.datasources.BenefitsDataSource
import by.akella.shared.domain.models.BenefitModel
import kotlinx.coroutines.flow.Flow

actual class BenefitsRemoteDataSource : BenefitsDataSource {

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