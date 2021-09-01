package by.akella.benefits.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DiscountView(modifier: Modifier = Modifier, text: String = "", textStyle: TextStyle = LocalTextStyle.current) {
    Cloud(modifier, backgroundColor = Color.Yellow, contentColor = Color.Black, text = "$text%", textStyle = textStyle)
}

@Composable
fun PromoView(modifier: Modifier = Modifier, text: String, textStyle: TextStyle = LocalTextStyle.current) {
    Cloud(modifier, backgroundColor = Color.Blue, contentColor = Color.White, text = text, textStyle = textStyle)
}

@Composable
fun Cloud(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    contentColor: Color,
    text: String,
    textStyle: TextStyle = LocalTextStyle.current
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = backgroundColor,
        contentColor = contentColor
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
            text = text,
            style = textStyle
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDiscountView() {
    Column {
        DiscountView(text = "10-15", textStyle = MaterialTheme.typography.h6)
        PromoView(text = "345678", textStyle = MaterialTheme.typography.h6)
    }
}
