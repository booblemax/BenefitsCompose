package by.akella.shared.data.datasources

import by.akella.shared.domain.models.BenefitModel
import kotlinx.coroutines.flow.Flow

interface BenefitsRemoteDataSource {

    suspend fun getBenefitList(force: Boolean): List<BenefitModel>
}