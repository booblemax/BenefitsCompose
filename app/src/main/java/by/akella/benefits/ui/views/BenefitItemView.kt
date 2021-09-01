package by.akella.benefits.ui.views

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import by.akella.benefits.R
import by.akella.benefits.domain.PlaceType
import by.akella.benefits.domain.PromoType
import by.akella.benefits.domain.models.BenefitModel
import by.akella.benefits.ui.views.EnumMapper.stringName

@Composable
fun BenefitItem(
    modifier: Modifier = Modifier,
    model: BenefitModel,
    isLoading: Boolean,
    onItemClicked: () -> Unit
) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(6.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .height(210.dp)
                    .padding(top = 12.dp)
                    .loadingShimmer(isLoading),
                painter = rememberImagePainter(model.icon),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Row(modifier = Modifier.padding(top = 12.dp)) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        modifier = Modifier.padding(start = 12.dp, end = 8.dp)
                            .fillMaxWidth()
                            .loadingShimmer(isLoading),
                        text = model.name,
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.padding(start = 12.dp, top = 8.dp, end = 8.dp)
                            .fillMaxWidth()
                            .loadingShimmer(isLoading),
                        text = stringName(model.type, context),
                        style = MaterialTheme.typography.subtitle2,
                        color = Color.Gray
                    )
                    if (model.site.isNotEmpty()) {
                        Text(
                            modifier = Modifier
                                .padding(top = 4.dp, start = 12.dp, end = 8.dp)
                                .fillMaxWidth()
                                .loadingShimmer(isLoading)
                                .apply {
                                    if (model.promoType == PromoType.WORD) {
                                        clickable {
                                            context.startActivity(
                                                Intent(Intent.ACTION_VIEW, Uri.parse(model.site))
                                            )
                                        }
                                    }
                                },
                            text = model.site,
                            style = MaterialTheme.typography.subtitle2,
                            color = Color.Gray,
                            textDecoration = TextDecoration.Underline,
                            softWrap = false
                        )
                    }
                }
                Column(horizontalAlignment = Alignment.End) {
                    DiscountView(
                        modifier = Modifier.padding(top = 4.dp, end = 12.dp)
                            .loadingShimmer(isLoading),
                        text = model.discount,
                        textStyle = MaterialTheme.typography.subtitle2
                    )
                    PromoView(
                        modifier = Modifier
                            .padding(top = 8.dp, end = 12.dp)
                            .clickable { copyPromoInClipboard(context, model.promo) }
                            .loadingShimmer(isLoading),
                        text = model.promoName(context)
                    )
                }
            }
            if (model.description.isNotEmpty() || isLoading) Description(isLoading, model.description)
        }
    }
}

@Composable
fun Description(isLoading: Boolean, description: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .loadingShimmer(isLoading),
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
        PromoType.BADGE -> context.getString(R.string.badge_name).uppercase()
        PromoType.WORD -> this.promo.uppercase()
    }

@Preview(showBackground = true)
@Composable
fun PreviewBenefitItemView() {
    BenefitItem(
        isLoading = true,
        model = BenefitModel(
            "1",
            "name1",
            PlaceType.SHOP,
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