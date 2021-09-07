package by.akella.benefits.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.akella.benefits.domain.auth.AuthController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CardViewModel(
    private val authController: AuthController
) : ViewModel() {

    private val mLogOutState = MutableStateFlow(false)
    val logOutState: StateFlow<Boolean> get() = mLogOutState

    fun logOut() {
        authController.logOut()
            .onEach { mLogOutState.value = it }
            .launchIn(viewModelScope)
    }
}