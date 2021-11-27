package by.akella.shared.data.datasources

import by.akella.shared.domain.models.UserModel
import kotlinx.coroutines.flow.Flow

interface UsersLocalDataSource {

    fun saveUserData(userModel: UserModel)

    fun getUserData(uid: String): Flow<UserModel?>
}