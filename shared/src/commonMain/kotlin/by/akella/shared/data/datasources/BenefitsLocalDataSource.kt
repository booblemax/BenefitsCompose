package by.akella.shared.data.datasources

import by.akella.shared.domain.models.BenefitModel

interface BenefitsLocalDataSource {

    suspend fun saveBenefits(items: List<BenefitModel>)

    suspend fun getBenefitList(): List<BenefitModel>
}