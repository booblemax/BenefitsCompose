package by.akella.benefits.domain.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import timber.log.Timber

interface AuthController {

    fun isSignedIn(): Boolean

    fun signInByEmailAndPassword(email: String, password: String): Flow<SignInState>
}

@ExperimentalCoroutinesApi
class AuthControllerImpl(
    private val auth: FirebaseAuth
) : AuthController {

    override fun isSignedIn(): Boolean = auth.currentUser != null

    override fun signInByEmailAndPassword(email: String, password: String): Flow<SignInState> = callbackFlow {
        send(SignInState.LOADING)
        auth.signInWithEmailAndPassword(email, password)
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

    }
}

enum class SignInState {
    LOADING,
    SUCCESS,
    FAILED
}