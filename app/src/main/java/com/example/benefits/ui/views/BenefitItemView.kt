package com.example.benefits.ui.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.benefits.R
import com.example.benefits.domain.models.AddressModel
import com.example.benefits.domain.models.BenefitModel
// добавить отступы между итемами,
// доделать на отображение всех списком без детальной информации,
// поправить итемы на нормальный вид
// добавить все скидк
@Composable
fun BenefitItem(modifier: Modifier, model: BenefitModel, onItemClicked: () -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 12.dp),
                painter = painterResource(id = R.drawable.ic_test),
                contentDescription = null
            )
            Row(modifier = Modifier.padding(top = 24.dp)) {
                Column(
                    modifier = Modifier.weight(1f).padding(end = 12.dp)
                ) {
                    Text(
                        text = model.name,
                        style = MaterialTheme.typography.h4
                    )
                    Text(
                        modifier = Modifier.padding(start = 12.dp),
                        text = model.address.toString(),
                        style = MaterialTheme.typography.subtitle1
                    )
                    Text(
                        modifier = Modifier.padding(start = 12.dp),
                        text = model.type,
                        style = MaterialTheme.typography.body2
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End,
                ) {
                    Spacer(modifier = Modifier.height(12.dp))
                    DiscountView(text = model.discount)
                    Spacer(modifier = Modifier.height(12.dp))
                    PromoView(text = model.promo, onClick = {
                        Toast.makeText(context, "promo clicked ${model.promo}", Toast.LENGTH_SHORT)
                            .show()
                    })
                }
            }
            Description(model.description)
        }
    }
}

@Composable
fun Description(description: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        color = Color.LightGray,

        shape = RoundedCornerShape(6.dp)
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            text = description
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBenefitItemView() {
    BenefitItem(
        model = BenefitModel(
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