package by.akella.shared.data.datasources

import by.akella.shared.domain.models.UserModel

interface UsersRemoteDataSource {

    suspend fun getUserData(uid: String): UserModel
}