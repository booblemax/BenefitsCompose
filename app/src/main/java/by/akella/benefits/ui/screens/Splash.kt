package by.akella.benefits.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import by.akella.benefits.BuildConfig
import by.akella.benefits.R
import by.akella.benefits.ui.navigation.Screens
import by.akella.benefits.ui.theme.BenefitsTheme
import by.akella.benefits.ui.theme.Blue700
import by.akella.benefits.ui.viewmodels.AuthState
import by.akella.benefits.ui.viewmodels.SplashViewModel
import by.akella.benefits.util.MAX_EMAIL_LENGTH
import by.akella.benefits.util.MAX_PASSWORD_LENGTH
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@ExperimentalAnimationApi
@Composable
fun Splash(
    viewModel: SplashViewModel,
    navigateTo: (String) -> Unit
) {
    val authState by viewModel.authState.collectAsState(initial = AuthState.Loading)

    if (authState !is AuthState.SignedIn) {
        LoginForm(authState) { email, pass -> viewModel.signIn(email, pass) }
    }

    LaunchedEffect(viewModel) {
        delay(800L)
        viewModel.checkIsAuth()
    }

    LaunchedEffect(authState) {
        if (authState is AuthState.SignedIn) {
            navigateTo(Screens.Home.screenName)
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun LoginForm(authState: AuthState, loginClick: (String, String) -> Unit) {
    val email = remember { mutableStateOf(if (BuildConfig.DEBUG) BuildConfig.USERNAME else "") }
    val password = remember { mutableStateOf(if (BuildConfig.DEBUG) BuildConfig.PASSWORD else "") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "logo",
            colorFilter = ColorFilter.tint(Blue700)
        )

        AnimatedVisibility(
            visible = authState is AuthState.Signing,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    modifier = Modifier.padding(top = 48.dp),
                    text = email.value,
                    hint = stringResource(R.string.login_hint_email),
                    isEnabled = authState !is AuthState.Signing.Loading,
                    onValueChange = { newValue ->
                        if (newValue.length <= MAX_EMAIL_LENGTH) email.value = newValue
                    })
                OutlinedTextField(
                    text = password.value,
                    hint = stringResource(R.string.login_hint_password),
                    isPassword = true,
                    isEnabled = authState !is AuthState.Signing.Loading,
                    onValueChange = { newValue ->
                        if (newValue.length <= MAX_PASSWORD_LENGTH) password.value = newValue
                    }
                )
            }
        }

        AnimatedVisibility(
            visible = authState is AuthState.Signing.Input.Error,
            enter = fadeIn(animationSpec = tween(800)) + expandVertically(animationSpec = tween(800)),
            exit = fadeOut(animationSpec = tween(800)) + shrinkVertically(animationSpec = tween(800))
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(R.string.login_label_error),
                style = TextStyle(
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        AnimatedVisibility(
            visible = authState is AuthState.Signing,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            LoadingButton(
                modifier = Modifier.padding(top = 12.dp),
                isLoading = authState is AuthState.Signing.Loading
            ) {
                loginClick(email.value, password.value)
            }
        }
    }
}

@Composable
fun LoadingButton(
    modifier: Modifier,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    val transition = updateTransition(isLoading, "LoadingButton")
    val width by transition.animateDp(label = "width", transitionSpec = { TweenSpec(350) }) {
        if (it) 64.dp else 278.dp
    }

    val alphaLoader by transition.animateFloat(
        label = "alphaLoader",
        transitionSpec = { TweenSpec(500) }) {
        if (it) 1f else 0f
    }
    val alphaText by transition.animateFloat(
        label = "alphaText",
        transitionSpec = { TweenSpec(500) }) {
        if (it) 0f else 1f
    }

    Button(
        modifier = modifier.size(width, 38.dp),
        onClick = onClick
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
                    .alpha(alphaLoader),
                color = Color.White
            )
        } else {
            Text(
                modifier = Modifier.alpha(alphaText),
                text = stringResource(R.string.login_button_sign_in),
                style = MaterialTheme.typography.button
            )
        }
    }
}

@Composable
fun OutlinedTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String,
    isPassword: Boolean = false,
    isEnabled: Boolean = true,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = onValueChange,
        singleLine = true,
        label = {
            Text(text = hint)
        },
        enabled = isEnabled,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
    )
}

@ExperimentalAnimationApi
@Preview(name = "LoginForm", showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginForm() {
    BenefitsTheme {
        LoginForm(AuthState.Signing.Input.Writing) { email, pass -> }
    }
}