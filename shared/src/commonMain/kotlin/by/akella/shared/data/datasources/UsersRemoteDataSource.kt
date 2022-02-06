package by.akella.shared.data.datasources

import by.akella.shared.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

interface UsersRemoteDataSource {

    fun getUserData(uid: String): Flow<UserModel>

    fun getImageByUrl(imageUrl: String): Flow<Any>
}