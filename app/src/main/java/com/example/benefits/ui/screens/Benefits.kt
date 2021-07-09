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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.benefits.domain.models.AddressModel
import com.example.benefits.domain.models.BenefitModel
import com.example.benefits.ui.Resource
import com.example.benefits.ui.navigation.Screens
import com.example.benefits.ui.viewmodels.BenefitsViewModel
import com.example.benefits.ui.views.BenefitItem
import com.example.benefits.ui.views.ErrorView
import com.example.benefits.ui.views.Loading
import org.koin.androidx.compose.getStateViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun Benefits(navigateTo: (String) -> Unit) {
    val viewModel = getViewModel<BenefitsViewModel>()
    val benefitsState = viewModel.benefitsState.collectAsState()

    Column {
        TopAppBar(title = { Text(text = "Benefits") })
        when (val value = benefitsState.value) {
            is Resource.Loading -> Loading()
            is Resource.Success<List<BenefitModel>> ->
                BenefitsList(value.data) { navigateTo(Screens.Details.createRoute(it.id)) }
            is Resource.Error -> ErrorView(error = value.error) {
                viewModel.loadData()
            }
        }
    }

    LaunchedEffect(key1 = Screens.Main) {
        viewModel.loadData()
    }
}

@Composable
fun BenefitsList(items: List<BenefitModel>, onItemClick: (BenefitModel) -> Unit) {
    LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
        items(items.size, { items[it].id }) { index ->
            val item = items[index]
            Spacer(modifier = Modifier.height(8.dp))
            BenefitItem(model = item) {
                onItemClick(item)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    BenefitsList(
        items = listOf(
            BenefitModel("1", "name1", "type1", AddressModel("city1", "street1"), "", "","","icon1"),
            BenefitModel("2", "name2", "type1", AddressModel("city1", "street1"),  "", "", "", "icon1"),
            BenefitModel("3", "name3", "type1", AddressModel("city1", "street1"),  "", "","", "icon1"),
            BenefitModel("4", "name4", "type1", AddressModel("city1", "street1"), "", "","", "icon1"),
            BenefitModel("5", "name5", "type1", AddressModel("city1", "street1"),  "", "","", "icon1"),
            BenefitModel("6", "name6", "type1", AddressModel("city1", "street1"), "", "", "","icon1"),
            BenefitModel("7", "name7", "type1", AddressModel("city1", "street1"), "", "", "","icon1"),
            BenefitModel("8", "name8", "type1", AddressModel("city1", "street1"), "", "","", "icon1"),
            BenefitModel("9", "name9", "type1", AddressModel("city1", "street1"), "", "","","icon1"),
            BenefitModel("10", "name10", "type1", AddressModel("city1", "street1"), "", "","","icon1"))
    ) {}
}