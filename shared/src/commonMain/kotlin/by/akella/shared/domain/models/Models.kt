package by.akella.shared.domain.models

import java.util.*

data class BenefitModel(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    val type: PlaceType = PlaceType.OTHER,
    val discount: String = "",
    val discountType: String = "",
    val promo: String = "",
    val promoType: PromoType = PromoType.BADGE,
    val site: String = "",
    val description: String = "",
    val icon: String = ""
)

data class UserModel(
    val uid: String = UUID.randomUUID().toString(),
    val fio: String = "",
    val city: String = "",
    val image: String = "",
    val position: String = ""
)

internal fun String.toPlaceType() =
    when (this.lowercase()) {
        "cafe" -> PlaceType.CAFE
        "shop" -> PlaceType.SHOP
        else -> PlaceType.OTHER
    }

internal fun String.toPromoType() =
    when (this.lowercase()) {
        "word" -> PromoType.WORD
        else -> PromoType.BADGE
    }