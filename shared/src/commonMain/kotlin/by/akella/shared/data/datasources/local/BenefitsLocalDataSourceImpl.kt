package by.akella.shared.data.datasources.local

import by.akella.shared.data.datasources.BenefitsLocalDataSource
import by.akella.shared.domain.models.*
import by.akella.shared.domain.models.toPlaceType
import by.akella.sqldelight.benefits.Benefits
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BenefitsLocalDataSourceImpl(
    private val benefits: Benefits
) : BenefitsLocalDataSource {

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

    override fun saveBenefits(items: List<BenefitModel>): Flow<Any> = flow {
        benefits.benefitsQueries.transaction {
            items.forEach {
                benefits.benefitsQueries.insert(
                    it.id, it.name, it.type.ordinal.toString(), it.discount, it.discountType, it.promo, it.promoType.ordinal.toString(), it.site, it.description, it.icon
                )
            }
        }
        emit(1)
    }

    override fun getBenefitList(): Flow<List<BenefitModel>> = flow {
        val list = benefits.benefitsQueries.getAll(benefitMapper).executeAsList()
        emit(list)
    }

    override fun getBenefitModel(uid: String): Flow<BenefitModel> = flow {
        benefits.benefitsQueries.getById(uid, benefitMapper)
    }
}