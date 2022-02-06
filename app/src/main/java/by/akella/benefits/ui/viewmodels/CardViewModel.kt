package by.akella.benefits.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.akella.shared.domain.auth.AuthController
import by.akella.shared.domain.models.UserModel
import by.akella.shared.domain.repos.UsersRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CardViewModel(
    private val authController: AuthController,
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val mCardState = MutableStateFlow<CardState>(CardState.Loading)
    val cardState: StateFlow<CardState> get() = mCardState

    fun loadUserData() {
        usersRepository.getUserData(authController.currentUserId)
            .catch { mCardState.value = CardState.Error.Fetching }
            .onEach { userModel ->
                mCardState.value = CardState.Success.DataLoaded(userModel)
                loadImage(userModel.image)
            }
            .launchIn(viewModelScope)
    }

    fun logOut() {
        authController.logOut()
            .onEach {
                mCardState.value =
                    if (it) CardState.LoggedOut
                    else CardState.Error.LoggedOut(mCardState.value.model)
            }
            .launchIn(viewModelScope)
    }

    private fun loadImage(imagePath: String) {
        viewModelScope.launch {
            usersRepository.loadImage(imagePath)
                .catch {
                    val model = mCardState.value.model.copy(image = "")
                    mCardState.value = CardState.Success.ImageLoaded(model)
                }
                .collect {
                    val model = mCardState.value.model.copy(image = it.toString())
                    mCardState.value = CardState.Success.ImageLoaded(model)
                }
        }
    }
}

sealed class CardState(val model: UserModel = UserModel()) {
    object Loading : CardState()
    sealed class Success(model: UserModel) : CardState(model) {
        class DataLoaded(model: UserModel) : Success(model)
        class ImageLoaded(model: UserModel) : Success(model)
    }
    object LoggedOut : CardState()
    sealed class Error(model: UserModel = UserModel()) : CardState(model) {
        class LoggedOut(model: UserModel) : Error(model)
        object Fetching : Error()
    }
}