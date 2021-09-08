package by.akella.benefits.data.datasource.remote.users

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

interface UsersRemoteApi {

    suspend fun getUserData(uid: String): UserResponse
}

@ExperimentalCoroutinesApi
class UsersRemoteApiImpl(
    private val db: FirebaseDatabase
) : UsersRemoteApi {

    override suspend fun getUserData(uid: String): UserResponse =
        suspendCancellableCoroutine { continuation ->
            db.reference.child("users").child(uid)
                .get()
                .addOnSuccessListener { result ->
                    if (result.exists()) {
                        result.getValue(UserResponse::class.java)?.let { userResponse ->
                            continuation.resume(userResponse.copy(uid = uid)) {
                                continuation.resumeWithException(it)
                            }
                        } ?: continuation.resumeWithException(NoSuchElementException())
                    }
                }
                .addOnFailureListener { fail -> continuation.resumeWithException(fail) }
        }
}