package by.akella.benefits.data.datasource.remote

import by.akella.benefits.domain.PromoType
import by.akella.benefits.domain.models.BenefitModel
import by.akella.benefits.domain.models.map
import com.google.gson.annotations.SerializedName

data class BenefitsResponse(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("discount")
    val discount: String = "",
    @SerializedName("discountType")
    val discountType: String = "",
    @SerializedName("promo")
    val promo: String = "",
    @SerializedName("site")
    val site: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("icon")
    val icon: String = ""
)

fun BenefitsResponse.toModel() =
    BenefitModel(
        id,
        name,
        type.map(),
        discount,
        discountType,
        promo,
        if (promo.isNotEmpty() && promo != "-1") PromoType.WORD else PromoType.BADGE,
        site,
        description,
        icon
    )
