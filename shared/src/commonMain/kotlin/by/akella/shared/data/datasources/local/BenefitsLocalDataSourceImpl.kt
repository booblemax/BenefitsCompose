package by.akella.shared.data.datasources.local

import by.akella.shared.data.datasources.BenefitsLocalDataSource
import by.akella.shared.data.datasources.BenefitsRemoteDataSource
import by.akella.shared.domain.models.BenefitModel
import by.akella.shared.domain.models.PlaceType
import by.akella.shared.domain.models.PromoType
import by.akella.sqldelight.benefits.Benefits
import kotlinx.coroutines.flow.Flow

class BenefitsLocalDataSourceImpl(
    private val benefits: Benefits
) : BenefitsLocalDataSource {

    override suspend fun saveBenefits(items: List<BenefitModel>) {
        benefits.benefitsQueries.transaction {
            items.forEach {
                benefits.benefitsQueries.insert(
                    it.id, it.name, it.type.ordinal.toString(), it.discount, it.discountType, it.promo, it.promoType.ordinal.toString(), it.site, it.description, it.icon
                )
            }
        }
    }

    override suspend fun getBenefitList(): List<BenefitModel> {
        return benefits.benefitsQueries.getAll { id, name, type, discount, discountType, promo, promoType, site, description, icon ->
            BenefitModel(
                id,
                name,
                PlaceType.values()[type.toInt()],
                discount,
                discountType,
                promo,
                PromoType.values()[promoType.toInt()],
                site,
                description,
                icon
            )
        }.executeAsList()
    }
}