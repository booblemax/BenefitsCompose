package com.example.benefits.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.benefits.R
import com.example.benefits.domain.models.AddressModel
import com.example.benefits.domain.models.BenefitModel

@Composable
fun BenefitItem(item: BenefitModel, onItemClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 12.dp)
            .clickable { onItemClicked() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_test),
            contentDescription = "image icon",
            modifier = Modifier.padding(start = 12.dp, top = 8.dp, bottom = 8.dp)
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp),
            text = item.name,
            style = MaterialTheme.typography.subtitle1
        )
        DiscountView(
            text = item.discount
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBenefitItemView() {
    BenefitItem(
        item = BenefitModel(
            "1",
            "name1",
            "type1",
            AddressModel("city1", "street1"),
            "10-15%",
            "",
            "",
            "icon1"
        )
    ) {}
}