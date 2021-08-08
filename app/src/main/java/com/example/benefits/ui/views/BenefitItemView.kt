package com.example.benefits.ui.views

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.benefits.R
import com.example.benefits.domain.models.AddressModel
import com.example.benefits.domain.models.BenefitModel
import com.example.benefits.domain.PlaceType
import com.example.benefits.domain.PromoType
import com.example.benefits.ui.views.EnumMapper.stringName

@Composable
fun BenefitItem(modifier: Modifier = Modifier, model: BenefitModel, onItemClicked: () -> Unit) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(6.dp),
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(210.dp)
                    .padding(top = 12.dp),
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null
            )
            Row(modifier = Modifier.padding(top = 12.dp)) {
                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = model.name,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                DiscountView(text = model.discount)
            }
            Row(modifier = Modifier.padding(top = 12.dp)) {
                Text(
                    modifier = Modifier.padding(start = 12.dp),
                    text = stringName(model.type, context),
                    style = MaterialTheme.typography.h6,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.weight(1f))
                PromoView(
                    text = model.promoName(context),
                    onClick = { copyPromoInClipboard(context, model.promo) }
                )
            }
            if (model.site.isNotEmpty()) {
                Text(
                    modifier =
                        Modifier
                            .padding(top = 4.dp, start = 12.dp)
                            .clickable { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(model.site))) },
                    text = model.site,
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.Gray,
                    textDecoration = TextDecoration.Underline
                )
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

private fun BenefitModel.promoName(context: Context): String =
    when (promoType) {
        PromoType.BADGE -> context.getString(R.string.badge_name)
        PromoType.WORD -> this.promo
    }

@Preview(showBackground = true)
@Composable
fun PreviewBenefitItemView() {
    BenefitItem(
        model = BenefitModel(
            "1",
            "name1",
            PlaceType.SHOP,
            AddressModel("city1", "street1"),
            "10-15",
            "",
            "12345",
            PromoType.WORD,
            "site.com",
            "описаниеописаниеописаниеописаниеописаниеописаниеописаниеописаниеописаниеописаниеописаниеописаниеописаниеописаниеописаниеописание"
        )
    ) {}
}

private fun copyPromoInClipboard(context: Context, promo: String) {
    val clipBoardManager =
        context.getSystemService(ClipboardManager::class.java) as ClipboardManager
    val clipData = ClipData(
        context.getString(R.string.clip_promo_description),
        arrayOf("plain/text"),
        ClipData.Item(promo)
    )
    clipBoardManager.setPrimaryClip(clipData)
    Toast.makeText(
        context,
        context.getString(R.string.clip_promo_copied_successfully),
        Toast.LENGTH_SHORT
    )
        .show()
}