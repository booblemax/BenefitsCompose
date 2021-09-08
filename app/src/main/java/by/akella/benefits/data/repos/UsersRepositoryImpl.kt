package by.akella.benefits.data.repos

import by.akella.benefits.data.datasource.local.dao.UsersDao
import by.akella.benefits.data.datasource.local.toEntity
import by.akella.benefits.data.datasource.local.toModel
import by.akella.benefits.data.datasource.remote.users.UsersRemoteApi
import by.akella.benefits.data.datasource.remote.users.toModel
import by.akella.benefits.domain.models.UserModel
import by.akella.benefits.domain.repos.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UsersRepositoryImpl(
    private val remote: UsersRemoteApi,
    private val local: UsersDao
) : UsersRepository {

    override fun getUserData(uid: String): Flow<UserModel> = flow {
        try {
            val remoteResult = remote.getUserData(uid).toModel()
            emit(remoteResult)
            local.insert(remoteResult.toEntity())
        } catch (e: Exception) {
            emit(local.getUserData(uid).toModel())
        }
    }
}