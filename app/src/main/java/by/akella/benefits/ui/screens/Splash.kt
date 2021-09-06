package by.akella.benefits.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.akella.benefits.BuildConfig
import by.akella.benefits.R
import by.akella.benefits.ui.navigation.Screens
import by.akella.benefits.ui.theme.BenefitsTheme
import by.akella.benefits.ui.theme.Blue700
import by.akella.benefits.ui.viewmodels.AuthState
import by.akella.benefits.ui.viewmodels.SplashViewModel
import by.akella.benefits.ui.views.Loading
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel
import util.MAX_EMAIL_LENGTH
import util.MAX_PASSWORD_LENGTH

@Composable
fun Splash(navigateTo: (String) -> Unit) {
    val viewModel = getViewModel<SplashViewModel>()
    val authState = viewModel.authState.collectAsState(initial = AuthState.Loading)

    if (authState.value is AuthState.SignedIn) navigateTo(Screens.HomeScreens.List.screenName)
    LoginForm(authState.value) { email, pass -> viewModel.signIn(email, pass) }

    LaunchedEffect(Screens.Splash) {
        delay(800L)
        viewModel.checkIsAuth()
    }
}

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

        if (authState is AuthState.Signing) {

            if (authState is AuthState.Signing.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(top = 24.dp).width(60.dp).height(60.dp)
                )
            }

            if (authState is AuthState.Signing.Input) {
                OutlinedTextField(
                    modifier = Modifier.padding(top = 48.dp),
                    text = email.value,
                    hint = stringResource(R.string.login_hint_email),
                    onValueChange = { newValue ->
                        if (newValue.length <= MAX_EMAIL_LENGTH) email.value = newValue
                    })
                OutlinedTextField(
                    text = password.value,
                    hint = stringResource(R.string.login_hint_password),
                    isPassword = true,
                    onValueChange = { newValue ->
                        if (newValue.length <= MAX_PASSWORD_LENGTH) password.value = newValue
                    }
                )
                if (authState is AuthState.Signing.Input.Error) {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringResource(R.string.login_label_error),
                        style = TextStyle(
                            color = Color.Red,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                Button(
                    modifier = Modifier.padding(top = 12.dp),
                    onClick = { loginClick(email.value, password.value) }) {
                    Text(text = stringResource(R.string.login_button_sign_in))
                }
            }
        }
    }
}

@Composable
fun OutlinedTextField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String,
    isPassword: Boolean = false,
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
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
    )
}

@Preview(name = "LoginForm", showBackground = true, showSystemUi = true)
@Composable
fun previewLoginForm() {
    BenefitsTheme {
        LoginForm(AuthState.Signing.Loading) { email, pass -> }
    }
}