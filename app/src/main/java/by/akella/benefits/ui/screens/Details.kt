package by.akella.benefits.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.akella.benefits.R
import by.akella.benefits.util.Resource
import by.akella.benefits.ui.navigation.Screens
import by.akella.benefits.ui.viewmodels.DetailsViewModel
import by.akella.benefits.ui.views.DiscountView
import by.akella.benefits.ui.views.EnumMapper.stringName
import by.akella.benefits.ui.views.ErrorView
import by.akella.benefits.ui.views.Loading
import by.akella.benefits.ui.views.PromoView
import by.akella.shared.domain.models.BenefitModel
import org.koin.androidx.compose.getViewModel

@Composable
fun Details(modelId: String, navBack: () -> Unit) {
    val viewModel = getViewModel<DetailsViewModel>()
    val modelState = viewModel.modelState.collectAsState()

    when (val value = modelState.value) {
        is Resource.Loading -> Loading()
        is Resource.Success<BenefitModel> ->
            DetailsContent(model = value.data) { navBack() }
        is Resource.Error -> ErrorView(error = value.error) {
            viewModel.loadBenefit(modelId)
        }
    }

    LaunchedEffect(key1 = Screens.Details.toString()) {
        viewModel.loadBenefit(modelId)
    }
}

@Composable
fun DetailsContent(
    model: BenefitModel,
    onNavBackClicked: () -> Unit
) {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Image(
            modifier = Modifier
                .size(32.dp)
                .padding(top = 16.dp)
                .clickable { onNavBackClicked() },
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
                    text = stringName(model.type, context),
                    style = MaterialTheme.typography.body2
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                DiscountView(text = model.discount, textStyle = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(12.dp))
                PromoView(text = model.promo,
                    textStyle = MaterialTheme.typography.h6,
                )
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
    DetailsContent(
        BenefitModel(),
        { }
    )
}

