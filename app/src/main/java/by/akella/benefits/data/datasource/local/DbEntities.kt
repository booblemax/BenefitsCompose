package by.akella.benefits.data.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.akella.benefits.domain.PromoType
import by.akella.benefits.domain.models.BenefitModel
import by.akella.benefits.domain.models.UserModel
import by.akella.benefits.domain.models.map

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

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val uid: String,
    val fio: String,
    val city: String,
    val image: String,
    val position: String
)

fun BenefitEntity.toModel() =
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

fun UserEntity.toModel() =
    UserModel(uid, fio, city, image, position)

fun UserModel.toEntity() =
    UserEntity(uid, fio, city, image, position)
