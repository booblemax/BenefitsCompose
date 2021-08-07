package com.example.benefits.domain.models

import android.os.Parcelable
import com.example.benefits.domain.PlaceType
import kotlinx.parcelize.Parcelize

@Parcelize
data class BenefitModel(
    val id: String = "",
    val name: String = "",
    val type: PlaceType = PlaceType.OTHER,
    val address: AddressModel = AddressModel(),
    val discount: String = "",
    val discountType: String = "",
    val promo: String = "",
    val site: String = "",
    val description: String = "",
    val icon: String = ""
) : Parcelable

@Parcelize
data class AddressModel(
    val city: String = "",
    val street: String = ""
) : Parcelable {

    override fun toString(): String = "$city, $street"
}

internal fun String.map() =
    when (this) {
        "cafe" -> PlaceType.CAFE
        "shop" -> PlaceType.SHOP
        else -> PlaceType.OTHER
    }