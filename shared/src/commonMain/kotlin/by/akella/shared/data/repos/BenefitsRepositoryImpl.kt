package by.akella.shared.data.repos

import by.akella.shared.domain.models.BenefitModel
import by.akella.shared.domain.models.toPlaceType
import by.akella.shared.domain.models.toPromoType
import by.akella.shared.domain.repos.BenefitsRepository
import byakellasqldelight.benefits.BenefitsQueries
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BenefitsRepositoryImpl(
    private val benefitsQueries: BenefitsQueries
) : BenefitsRepository {

    private val benefitMapper =
        { id: String, name: String, type: String, discount: String, discountType: String,
          promo: String, promoType: String, site: String, description: String, icon: String ->
            BenefitModel(
                id,
                name,
                type.toPlaceType(),
                discount,
                discountType,
                promo,
                promoType.toPromoType(),
                site,
                description,
                icon
            )
        }


    override suspend fun saveBenefits(benefits: List<BenefitModel>) {
        benefitsQueries.transaction {
            benefits.forEach {
                benefitsQueries.insert(
                    it.id,
                    it.name,
                    it.type.name,
                    it.discount,
                    it.discountType,
                    it.promo,
                    it.promoType.name,
                    it.site,
                    it.description,
                    it.icon
                )
            }
        }
    }

    override fun getBenefitList(force: Boolean): Flow<List<BenefitModel>> = flow {
        val benefits = benefitsQueries.getAll(benefitMapper).executeAsList()
        emit(benefits)
    }

    override fun getBenefit(id: String): Flow<BenefitModel> = flow {
        val model = benefitsQueries.getById(id, benefitMapper).executeAsOne()
        emit(model)
    }
}