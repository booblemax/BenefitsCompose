package by.akella.shared.data.repos

import by.akella.shared.domain.models.UserModel
import by.akella.shared.domain.repos.UsersRepository
import byakellasqldelight.benefits.UsersQueries
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UsersRepositoryImpl(
    private val usersQueries: UsersQueries
) : UsersRepository {

    private val userMapper = { uid: String, fio: String, city: String, image: String, position: String ->
        UserModel(uid, fio, city, image, position)
    }

    override fun getUserData(uid: String): Flow<UserModel> = flow {
        val model = usersQueries.getUserData(uid, userMapper).executeAsOne()
        emit(model)
    }
}