package by.akella.shared.data.datasources.local

import by.akella.shared.data.datasources.UsersLocalDataSource
import by.akella.shared.domain.models.UserModel
import byakellasqldelight.benefits.Users

class UsersLocalDataSourceImpl(
    private val users: Users
) : UsersLocalDataSource {

    override suspend fun getUserData(uid: String): UserModel {
        TODO("Not yet implemented")
    }
}