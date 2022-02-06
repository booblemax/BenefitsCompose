package by.akella.shared.domain.auth

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface AuthController {

    val currentUserId: String

    fun isSignedIn(): Boolean

    fun signInByEmailAndPassword(email: String, password: String): Flow<SignInState>

    fun logOut(): Flow<Boolean>
}

@ExperimentalCoroutinesApi
class AuthControllerImpl() : AuthController {

    private val auth = Firebase.auth

    override val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    override fun isSignedIn(): Boolean = auth.currentUser != null

    override fun signInByEmailAndPassword(email: String, password: String): Flow<SignInState> =
        flow {
            emit(SignInState.LOADING)

            auth.signInWithEmailAndPassword(email, password).user?.let {
                emit(SignInState.SUCCESS)
            } ?: emit(SignInState.FAILED)
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