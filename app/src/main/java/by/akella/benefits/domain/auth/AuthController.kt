package by.akella.benefits.domain.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.coroutineContext
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import kotlin.coroutines.resumeWithException

interface AuthController {

    val currentUserId: String

    fun isSignedIn(): Boolean

    fun signInByEmailAndPassword(email: String, password: String): Flow<SignInState>

    fun logOut(): Flow<Boolean>
}

@ExperimentalCoroutinesApi
class AuthControllerImpl(
    private val auth: FirebaseAuth
) : AuthController {

    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override fun isSignedIn(): Boolean = auth.currentUser != null

    override fun signInByEmailAndPassword(email: String, password: String): Flow<SignInState> =
        callbackFlow {
            send(SignInState.LOADING)

            val task = auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    this.launch {
                        if (it.isSuccessful) {
                            send(SignInState.SUCCESS)
                        } else {
                            Timber.e(it.exception)
                            send(SignInState.FAILED)
                        }
                    }
                }

            awaitClose { task.isCanceled }
        }

    override fun logOut(): Flow<Boolean> = flow {
        auth.signOut()
        emit(auth.currentUser == null)
    }
}

enum class SignInState {
    LOADING,
    SUCCESS,
    FAILED
}