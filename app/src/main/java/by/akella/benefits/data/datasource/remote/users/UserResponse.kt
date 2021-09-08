package by.akella.benefits.data.datasource.remote.users

import by.akella.benefits.domain.models.UserModel
import java.util.*

data class UserResponse(
    val uid: String = UUID.randomUUID().toString(),
    val fio: String = "",
    val city: String = "",
    val image: String = "",
    val position: String = ""
)

fun UserResponse.toModel(): UserModel =
    UserModel(uid, fio, city, image, position)