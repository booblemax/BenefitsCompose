package by.akella.shared.data.datasources.remote

import by.akella.shared.data.datasources.BenefitsRemoteDataSource
import by.akella.shared.domain.models.BenefitModel

class BenefitsRemoteDataSourceImpl : BenefitsRemoteDataSource {

    override suspend fun getBenefitList(force: Boolean): List<BenefitModel> {
        TODO("Not yet implemented")
    }
}