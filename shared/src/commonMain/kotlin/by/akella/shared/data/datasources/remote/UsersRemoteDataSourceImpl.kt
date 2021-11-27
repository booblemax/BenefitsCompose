package by.akella.shared.data.datasources.remote

import by.akella.shared.data.datasources.UsersRemoteDataSource
import by.akella.shared.domain.models.UserModel

class UsersRemoteDataSourceImpl : UsersRemoteDataSource {

    override suspend fun getUserData(uid: String): UserModel {
        TODO("Not yet implemented")
    }
}