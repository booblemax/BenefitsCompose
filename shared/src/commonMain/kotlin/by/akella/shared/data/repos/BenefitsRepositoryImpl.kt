package by.akella.shared.data.repos

import by.akella.shared.data.datasources.BenefitsLocalDataSource
import by.akella.shared.data.datasources.BenefitsRemoteDataSource
import by.akella.shared.domain.models.BenefitModel
import by.akella.shared.domain.models.toPlaceType
import by.akella.shared.domain.models.toPromoType
import by.akella.shared.domain.repos.BenefitsRepository
import byakellasqldelight.benefits.BenefitsQueries
import kotlinx.coroutines.flow.*

class BenefitsRepositoryImpl(
    private val remote: BenefitsRemoteDataSource,
    private val local: BenefitsLocalDataSource
) : BenefitsRepository {

    override fun saveBenefits(benefits: List<BenefitModel>) {
        local.saveBenefits(benefits)
    }

    override fun getBenefitList(force: Boolean): Flow<List<BenefitModel>> =
        if (force) {
            remote.getBenefitList()
                .map {
                    saveBenefits(it)
                    it
                }
                .catch {
                    emitAll(local.getBenefitList())
                }
        } else local.getBenefitList()


    override fun getBenefit(id: String): Flow<BenefitModel> = local.getBenefitModel(id)
}