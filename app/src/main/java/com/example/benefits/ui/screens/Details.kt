package com.example.benefits.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
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
import com.example.benefits.ui.navigation.Router
import com.example.benefits.ui.navigation.Screen
import com.example.benefits.ui.views.DiscountView
import com.example.benefits.ui.views.PromoView

@Composable
fun Details(router: Router, model: BenefitModel) {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Image(
            modifier = Modifier
                .size(32.dp)
                .padding(top = 16.dp)
                .clickable { router.back() },
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 12.dp),
            painter = painterResource(id = R.drawable.ic_test),
            contentDescription = null
        )
        Row(modifier = Modifier.padding(top = 24.dp)) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = model.name,
                    style = MaterialTheme.typography.h4
                )
                Text(
                    text = model.address.toString(),
                    style = MaterialTheme.typography.subtitle1
                )
                Text(
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
fun DetailsScreenPreview() {
    Details(
        object : Router {
            override fun navigateTo(screen: Screen) {
                TODO("Not yet implemented")
            }

            override fun back() {
                TODO("Not yet implemented")
            }
        },
        BenefitModel(
            "1",
            "Vladka1",
            "vladka1",
            AddressModel("vladka1", "vladka1"),
            "",
            "",
            "ashdlkjahsdlfkjhaslkdjhflakjshdfkljahskldjfklajshdflkjhaslkdhf",
            "icon1"
        )
    )
}

