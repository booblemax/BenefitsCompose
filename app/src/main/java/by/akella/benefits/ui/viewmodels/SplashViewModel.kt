package by.akella.benefits.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import util.isValidEmail

class SplashViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val mAuthState = MutableSharedFlow<AuthState>()
    val authState: SharedFlow<AuthState> get() = mAuthState

    suspend fun checkIsAuth() {
        mAuthState.emit(if (auth.currentUser != null) AuthState.SignedIn else AuthState.Signing.Input.Writing)
    }

    fun signIn(email: String, pass: String) {
        viewModelScope.launch {
            if (!email.isValidEmail()) mAuthState.emit(AuthState.Signing.Input.Error)

            mAuthState.emit(AuthState.Signing.Loading)

            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener {
                    viewModelScope.launch {
                        if (it.isSuccessful) {
                            mAuthState.emit(AuthState.SignedIn)
                        } else {
                            Timber.e(it.exception)
                            mAuthState.emit(AuthState.Signing.Input.Error)
                        }
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