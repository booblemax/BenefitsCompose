package by.akella.benefits.domain.models

import android.os.Parcelable
import by.akella.benefits.domain.PlaceType
import by.akella.benefits.domain.PromoType
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
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
) : Parcelable

internal fun String.map() =
    when (this) {
        "cafe" -> PlaceType.CAFE
        "shop" -> PlaceType.SHOP
        else -> PlaceType.OTHER
    }