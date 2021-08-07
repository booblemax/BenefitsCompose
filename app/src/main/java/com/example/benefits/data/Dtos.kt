package com.example.benefits.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.benefits.data.converter.AddressConverter
import com.example.benefits.domain.models.AddressModel
import com.example.benefits.domain.models.BenefitModel
import com.example.benefits.domain.models.map

@Entity(tableName = "benefits")
@TypeConverters(AddressConverter::class)
data class BenefitEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String,
    val address: AddressDto,
    val discount: String,
    val discountType: String,
    val promo: String,
    val site: String,
    val description: String,
    val icon: String
)

data class AddressDto(
    val city: String,
    val street: String
)

fun BenefitEntity.toModel() =
    BenefitModel(
        id,
        name,
        type.map(),
        address.toModel(),
        discount,
        discountType,
        promo,
        description,
        icon
    )

fun BenefitModel.toEntity() =
    BenefitEntity(
        id,
        name,
        type.name.lowercase(),
        address.toDto(),
        discount,
        discountType,
        promo,
        site,
        description,
        icon
    )

fun AddressDto.toModel() =
    AddressModel(
        city,
        street
    )

fun AddressModel.toDto() =
    AddressDto(
        city,
        street
    )