package com.example.benefits.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.benefits.domain.PromoType
import com.example.benefits.domain.models.BenefitModel
import com.example.benefits.domain.models.map

@Entity(tableName = "benefits")
data class BenefitEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String,
    val discount: String,
    val discountType: String,
    val promo: String,
    val site: String,
    val description: String,
    val icon: String
)

fun BenefitEntity.toModel() =
    BenefitModel(
        id,
        name,
        type.map(),
        discount,
        discountType,
        promo,
        if (promo.isNotEmpty()) PromoType.WORD else PromoType.BADGE,
        site,
        description,
        icon
    )

fun BenefitModel.toEntity() =
    BenefitEntity(
        id,
        name,
        type.name.lowercase(),
        discount,
        discountType,
        promo,
        site,
        description,
        icon
    )