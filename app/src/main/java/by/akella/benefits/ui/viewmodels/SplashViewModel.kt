package by.akella.benefits.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.akella.benefits.domain.auth.AuthController
import by.akella.benefits.domain.auth.SignInState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import util.isValidEmail

class SplashViewModel(
    private val auth: AuthController
) : ViewModel() {

    private val mAuthState = MutableSharedFlow<AuthState>()
    val authState: SharedFlow<AuthState> get() = mAuthState

    suspend fun checkIsAuth() {
        mAuthState.emit(
            if (auth.isSignedIn()) AuthState.SignedIn else AuthState.Signing.Input.Writing
        )
    }

    fun signIn(email: String, pass: String) {
        viewModelScope.launch { if (!email.isValidEmail()) mAuthState.emit(AuthState.Signing.Input.Error) }

        auth.signInByEmailAndPassword(email, pass)
            .onEach {
                val state = when (it) {
                    SignInState.LOADING -> AuthState.Signing.Loading
                    SignInState.SUCCESS -> AuthState.SignedIn
                    SignInState.FAILED -> AuthState.Signing.Input.Error
                }

                mAuthState.emit(state)
            }
            .launchIn(viewModelScope)
    }
}

sealed class AuthState {
    object Loading : AuthState()
    object SignedIn : AuthState()
    sealed class Signing : AuthState() {
        object Loading : Signing()
        sealed class Input : Signing() {
            object Writing : Input()
            object Error : Input()
        }
    }
}