package by.akella.shared.data.repos

import by.akella.shared.data.datasources.UsersLocalDataSource
import by.akella.shared.data.datasources.UsersRemoteDataSource
import by.akella.shared.domain.models.UserModel
import by.akella.shared.domain.repos.UsersRepository
import byakellasqldelight.benefits.UsersQueries
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
class UsersRepositoryImpl(
    private val remote: UsersRemoteDataSource,
    private val local: UsersLocalDataSource
) : UsersRepository {

    override fun getUserData(uid: String): Flow<UserModel> =
        local.getUserData(uid)
            .flatMapLatest { model ->
               if (model != null) flow { emit(model) }
               else remote.getUserData(uid).map { local.saveUserData(it); it }
            }

    override fun loadImage(imagePath: String): Flow<Any> = remote.getImageByUrl(imagePath)
}