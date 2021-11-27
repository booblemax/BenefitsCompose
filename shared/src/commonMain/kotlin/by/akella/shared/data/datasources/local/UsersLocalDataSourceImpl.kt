package by.akella.shared.data.datasources.local

import by.akella.shared.data.datasources.UsersLocalDataSource
import by.akella.shared.domain.models.UserModel
import by.akella.sqldelight.benefits.Benefits
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UsersLocalDataSourceImpl(
    private val users: Benefits
) : UsersLocalDataSource {

    private val userMapper = { uid: String, fio: String, city: String, image: String, position: String ->
        UserModel(uid, fio, city, image, position)
    }

    override fun saveUserData(userModel: UserModel) {
        users.usersQueries.insert(
            userModel.uid, userModel.fio, userModel.city, userModel.image, userModel.position
        )
    }

    override fun getUserData(uid: String): Flow<UserModel?> = flow {
        val user = users.usersQueries.getUserData(uid, userMapper).executeAsOneOrNull()
        emit(user)
    }
}