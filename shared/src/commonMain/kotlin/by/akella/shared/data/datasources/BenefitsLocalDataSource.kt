package by.akella.shared.data.datasources

import by.akella.shared.domain.models.BenefitModel
import kotlinx.coroutines.flow.Flow

interface BenefitsLocalDataSource {

    fun saveBenefits(items: List<BenefitModel>): Flow<Any>

    fun getBenefitList(): Flow<List<BenefitModel>>

    fun getBenefitModel(uid: String): Flow<BenefitModel>
}