package by.akella.shared.data.datasources.remote

import by.akella.shared.data.datasources.UsersRemoteDataSource
import by.akella.shared.domain.models.UserModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.database
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class UsersRemoteDataSourceImpl : UsersRemoteDataSource {

    override fun getUserData(uid: String): Flow<UserModel> =
        Firebase.database.reference("users").child(uid)
            .valueEvents.map { it.value() }

    override fun getImageByUrl(imageUrl: String): Flow<Any> = flow {
        emit("")
    }
}