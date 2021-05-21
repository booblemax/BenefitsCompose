package com.example.benefits.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.benefits.domain.models.AddressModel
import com.example.benefits.domain.models.BenefitModel
import com.example.benefits.ui.Resource
import com.example.benefits.ui.navigation.Router
import com.example.benefits.ui.navigation.Screens
import com.example.benefits.ui.viewmodels.BenefitsViewModel
import com.example.benefits.ui.views.BenefitItem
import com.example.benefits.ui.views.Loading

@Composable
fun Benefits(router: Router) {
    val viewModel = remember { BenefitsViewModel() }
    val benefitsState = viewModel.benefitsState.collectAsState()

    Column {
        TopAppBar(title = { Text(text = "Benefits") })
        when (val value = benefitsState.value) {
            is Resource.Loading -> Loading()
            is Resource.Success<List<BenefitModel>> ->
                BenefitsList(value.data) { router.navigateTo(Screens.DETAILS) }
            is Resource.Error -> {
            }
            else -> {
            }
        }
    }
}

@Composable
fun BenefitsList(items: List<BenefitModel>, onItemClick: (BenefitModel) -> Unit) {
    LazyColumn {
        items(items.size, { items[it].id }) { index ->
            val item = items[index]
            BenefitItem(item = item) {
                onItemClick(item)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    val router = object : Router {
        override fun navigateTo(screen: Screens, launchSingleTop: Boolean) {
            TODO("Not yet implemented")
        }

        override fun back() {
            TODO("Not yet implemented")
        }
    }
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