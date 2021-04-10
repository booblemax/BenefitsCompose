package com.example.benefits.domain.models

data class BenefitModel(
    val id: String,
    val name: String,
    val type: String,
    val address: AddressModel,
    val discount: String,
    val promo: String,
    val description: String,
    val icon: String)

data class AddressModel(
    val city: String,
    val street: String
) {

    override fun toString(): String = "$city, $street"
}
