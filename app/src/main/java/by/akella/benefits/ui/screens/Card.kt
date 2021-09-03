package by.akella.benefits.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.akella.benefits.R
import by.akella.benefits.ui.theme.Blue700

@Composable
fun Card() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp, 48.dp),
        elevation = 4.dp,
        border = BorderStroke(2.dp, Color.LightGray),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp, start = 64.dp, end = 64.dp)
                    .clip(CircleShape)
                    .border(
                        BorderStroke(4.dp, Blue700),
                        CircleShape
                    ),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.padding(top = 24.dp, bottom = 4.dp),
                text = "Maxim Astapenko",
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Android Developer",
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

@Preview(showSystemUi = true, showBackground = true, device = Devices.PIXEL_2)
@Composable
fun previewCard() {
    Card()
}