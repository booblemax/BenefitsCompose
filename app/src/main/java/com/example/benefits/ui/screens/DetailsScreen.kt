package com.example.benefits.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.benefits.R
import com.example.benefits.domain.models.AddressModel
import com.example.benefits.domain.models.BenefitModel
import com.example.benefits.ui.views.DiscountView

@Composable
fun DetailsScreen(model: BenefitModel) {
    Column {
        Image(
            modifier = Modifier,
            painter = painterResource(id = R.drawable.ic_test),
            contentDescription = null
        )
        Row {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Name",
                    style = MaterialTheme.typography.h4
                )
                Text(
                    text = "type",
                    style = MaterialTheme.typography.subtitle1
                )
            }
            DiscountView(text = "10-15%")
        }
        Text(
            text = "address",
            style = MaterialTheme.typography.subtitle1
        )
        Description("descriptiondescriptionlsdkhflkahsdfajhsdlfkjahsdklfhjalksdjhfaklsjdf")
    }
}

@Composable
fun Description(description: String) {
    Surface(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        color = Color.LightGray,
        shape = MaterialTheme.shapes.large
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            text = description
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(
        BenefitModel("1", "name1", "type1", AddressModel("city1", "street1"), "", "","", "icon1"),
    )
}

