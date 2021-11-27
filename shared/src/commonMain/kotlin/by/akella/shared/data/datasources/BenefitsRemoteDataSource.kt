package by.akella.shared.data.datasources

import by.akella.shared.domain.models.BenefitModel
import kotlinx.coroutines.flow.Flow

interface BenefitsRemoteDataSource {

    fun getBenefitList(): Flow<List<BenefitModel>>
}