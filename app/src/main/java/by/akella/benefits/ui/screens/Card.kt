package by.akella.benefits.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.dp
import by.akella.benefits.R
import by.akella.benefits.domain.models.UserModel
import by.akella.benefits.ui.navigation.Screens
import by.akella.benefits.ui.theme.Blue700
import by.akella.benefits.ui.viewmodels.CardState
import by.akella.benefits.ui.viewmodels.CardViewModel
import by.akella.benefits.ui.views.ErrorView
import by.akella.benefits.ui.views.Loading
import by.akella.benefits.util.CommonError
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.getViewModel

@ExperimentalCoilApi
@ExperimentalUnitApi
@Composable
fun Card(navigateTo: (String) -> Unit) {
    val systemUiController = rememberSystemUiController()
    val color = MaterialTheme.colors.surface

    val viewModel = getViewModel<CardViewModel>()
    val cardState = viewModel.cardState.collectAsState()

    CardStateScreen(state = cardState.value, viewModel = viewModel, navigateTo = navigateTo)

    LaunchedEffect(key1 = Screens.HomeScreens.Card.screenName) {
        systemUiController.setStatusBarColor(color)
        viewModel.loadUserData()
    }
}

@Composable
fun CardStateScreen(state: CardState, viewModel: CardViewModel, navigateTo: (String) -> Unit) {
    when (state) {
        CardState.Loading -> Loading()
        is CardState.LoggedOut -> navigateTo(Screens.Splash.screenName)
        is CardState.Success -> UserCard(state.model, viewModel::logOut)
        is CardState.Error.Fetching -> ErrorView(
            CommonError("Что-то пошло не так..."),
            viewModel::loadUserData
        )
        is CardState.Error.LoggedOut -> {
            UserCard(state.model, viewModel::logOut)
            Snackbar { Text(text = "Не получилось выйти из аккаунта :(") }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun UserCard(
    model: UserModel,
    logOutClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp, 48.dp),
        elevation = 4.dp,
        border = BorderStroke(2.dp, Color.LightGray),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier
                    .size(48.dp)
                    .padding(top = 16.dp, end = 16.dp)
                    .align(Alignment.TopEnd)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(false),
                        role = Role.Button,
                        onClick = logOutClick
                    ),
                painter = painterResource(id = R.drawable.ic_exit_24),
                contentDescription = stringResource(id = R.string.card_log_out_content_description),
                colorFilter = ColorFilter.tint(Blue700)
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Avatar(url = model.image)
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.padding(top = 24.dp, bottom = 4.dp),
                    text = model.fio,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = model.position,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.SemiBold
                )
                Divider(
                    modifier = Modifier
                        .width(250.dp)
                        .padding(vertical = 12.dp),
                    color = Color.Black,
                    thickness = 2.dp
                )
                Image(
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .height(50.dp),
                    painter = painterResource(R.drawable.ic_logo),
                    colorFilter = ColorFilter.tint(Blue700),
                    contentDescription = null
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun Avatar(url: String?) {
    val painter = if (url.isNullOrEmpty()) {
        painterResource(id = R.drawable.ic_penguin)
    } else {
        rememberImagePainter(url) {
            placeholder(R.drawable.ic_penguin)
            error(R.drawable.ic_penguin)
        }
    }
    Image(
        modifier = Modifier
            .padding(top = 48.dp)
            .size(200.dp)
            .clip(CircleShape)
            .border(
                BorderStroke(4.dp, Blue700),
                CircleShape
            ),
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true, device = Devices.PIXEL_2)
@Composable
fun PreviewCard() {
    UserCard(
        UserModel(
            fio = "Максим Астапенко",
            city = "MINSK",
            image = "",
            position = "Android Developer"
        )
    ) { }
}