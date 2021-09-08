package by.akella.benefits.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.akella.benefits.util.CommonError
import timber.log.Timber

@Composable
fun ErrorView(error: CommonError, retry: () -> Unit) {
    Timber.e(error.e)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .padding(16.dp)
                .align(CenterHorizontally),
            text = error.message,
            fontSize = 20.sp
        )
        Button(
            onClick = { retry() },
            modifier = Modifier.align(CenterHorizontally)
        ) {
            Text(text = "Повторить")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorViewPreview() {
    ErrorView(CommonError("Error", Exception())) {}
}