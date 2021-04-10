package com.example.benefits.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.benefits.ui.theme.Yellow

@Composable
fun DiscountView(modifier: Modifier = Modifier, text: String = "") {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = Color.Yellow,
        elevation = 8.dp
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDiscountView() {
    DiscountView(text = "10-15%")
}
