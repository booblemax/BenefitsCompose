package by.akella.benefits.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import by.akella.benefits.R

@Composable
fun Test() {
    val state = remember { mutableStateOf(false)}

    LoadingButton1(modifier = Modifier, isLoading = state.value) {
        state.value = state.value.xor(true)
    }
}
@Composable
fun LoadingButton1(
    modifier: Modifier,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    val transition = updateTransition(isLoading, "LoadingButton")
    val width by transition.animateDp(label = "width", transitionSpec = { TweenSpec(800) }) {
        if (it) 64.dp else 278.dp
    }

    val alphaLoader by transition.animateFloat(
        label = "alphaLoader",
        transitionSpec = { TweenSpec(800) }) {
        if (it) 1f else 0f
    }
    val alphaText by transition.animateFloat(
        label = "alphaText",
        transitionSpec = { TweenSpec(800) }) {
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
                color = MaterialTheme.colors.surface
            )
        } else {
            Text(
                modifier = Modifier.alpha(alphaText),
                text = stringResource(R.string.login_button_sign_in)
            )
        }
    }
}