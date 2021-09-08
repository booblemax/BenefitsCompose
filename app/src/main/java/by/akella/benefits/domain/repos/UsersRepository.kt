package by.akella.benefits.domain.repos

import by.akella.benefits.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    fun getUserData(uid: String): Flow<UserModel>
}