package by.akella.shared.domain.repos

import by.akella.shared.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    fun getUserData(uid: String): Flow<UserModel>

    fun loadImage(imagePath: String): Flow<Any>
}