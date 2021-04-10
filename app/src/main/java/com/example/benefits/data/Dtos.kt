package com.example.benefits.data

import com.example.benefits.domain.models.AddressModel
import com.example.benefits.domain.models.BenefitModel

data class BenefitDto(
    val id: String,
    val name: String,
    val type: String,
    val address: AddressDto,
    val discount: String,
    val promo: String,
    val description: String,
    val icon: String
)

data class AddressDto(
    val city: String,
    val street: String
)

fun BenefitDto.toModel() =
    BenefitModel(
        id,
        name,
        type,
        address.toModel(),
        discount,
        promo,
        description,
        icon
    )

fun AddressDto.toModel() =
    AddressModel(
        city,
        street
    )