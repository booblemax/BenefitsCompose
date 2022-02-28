package by.akella.benefits.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.akella.shared.domain.auth.AuthController
import by.akella.shared.domain.auth.SignInState
import kotlinx.coroutines.launch
import by.akella.benefits.util.isValidEmail
import kotlinx.coroutines.flow.*

class SplashViewModel(
    private val auth: AuthController
) : ViewModel() {

    private val mAuthState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState: StateFlow<AuthState> get() = mAuthState

    suspend fun checkIsAuth() {
        mAuthState.emit(
            if (auth.isSignedIn()) AuthState.SignedIn else AuthState.Signing.Input.Writing
        )
    }

    fun signIn(email: String, pass: String) {
        viewModelScope.launch {
            if (!email.isValidEmail()) {
                mAuthState.emit(AuthState.Signing.Input.Error)
            } else {
                auth.signInByEmailAndPassword(email, pass)
                    .collect {
                        val state = when (it) {
                            SignInState.LOADING -> AuthState.Signing.Loading
                            SignInState.SUCCESS -> AuthState.SignedIn
                            SignInState.FAILED -> AuthState.Signing.Input.Error
                        }

                        mAuthState.emit(state)
                    }
            }
        }
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