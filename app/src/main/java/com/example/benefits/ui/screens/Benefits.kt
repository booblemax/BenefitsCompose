package com.example.benefits.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.benefits.domain.models.BenefitModel
import com.example.benefits.domain.PlaceType
import com.example.benefits.domain.PromoType
import com.example.benefits.ui.Resource
import com.example.benefits.ui.navigation.Screens
import com.example.benefits.ui.viewmodels.BenefitsViewModel
import com.example.benefits.ui.views.BenefitItem
import com.example.benefits.ui.views.ErrorView
import org.koin.androidx.compose.getViewModel

@Composable
fun Benefits(navigateTo: (String) -> Unit) {
    val viewModel = getViewModel<BenefitsViewModel>()
    val benefitsState = viewModel.benefitsState.collectAsState()

    Column {
        TopAppBar(title = { Text(text = "Benefits") })
        when (val value = benefitsState.value) {
            is Resource.Loading ->
                BenefitsList(true, listOf(BenefitModel(), BenefitModel(), BenefitModel(), BenefitModel(), BenefitModel())) { navigateTo(Screens.Details.createRoute(it.id)) }
            is Resource.Success<List<BenefitModel>> ->
                BenefitsList(false, value.data) { navigateTo(Screens.Details.createRoute(it.id)) }
            is Resource.Error -> ErrorView(error = value.error) {
                viewModel.loadData()
            }
        }
    }

    LaunchedEffect(key1 = Screens.HomeScreens.List) {
        viewModel.loadData()
    }
}

@Composable
fun BenefitsList(isLoading: Boolean, items: List<BenefitModel>, onItemClick: (BenefitModel) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(items.size, { items[it].id }) { index ->
            val item = items[index]
            Spacer(modifier = Modifier.height(8.dp))
            BenefitItem(model = item, isLoading = isLoading) {
                onItemClick(item)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    BenefitsList(
        false,
        items = listOf(
            BenefitModel("1", "name1", PlaceType.CAFE, "", "","", PromoType.BADGE,"icon1"),
            BenefitModel("2", "name2", PlaceType.CAFE,  "", "", "", PromoType.BADGE, "icon1"),
            BenefitModel("3", "name3", PlaceType.CAFE,  "", "","", PromoType.BADGE, "icon1"),
            BenefitModel("4", "name4", PlaceType.CAFE, "", "","", PromoType.BADGE, "icon1"),
            BenefitModel("5", "name5", PlaceType.CAFE,  "", "","", PromoType.BADGE, "icon1"),
            BenefitModel("6", "name6", PlaceType.CAFE, "", "", "", PromoType.BADGE,"icon1"),
            BenefitModel("7", "name7", PlaceType.CAFE, "", "", "", PromoType.BADGE,"icon1"),
            BenefitModel("8", "name8", PlaceType.CAFE, "", "","", PromoType.BADGE, "icon1"),
            BenefitModel("9", "name9", PlaceType.CAFE, "", "","", PromoType.BADGE,"icon1"),
            BenefitModel("10", "name10", PlaceType.CAFE, "", "","", PromoType.BADGE,"icon1"))
    ) {}
}