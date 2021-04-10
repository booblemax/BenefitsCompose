package com.example.benefits.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.benefits.domain.models.AddressModel
import com.example.benefits.domain.models.BenefitModel
import com.example.benefits.ui.Resource
import com.example.benefits.ui.viewmodels.BenefitsViewModel
import com.example.benefits.ui.views.BenefitItem
import com.example.benefits.ui.views.Loading

@Composable
fun BenefitsScreen() {
    val viewModel = remember { BenefitsViewModel() }
    val benefitsState = viewModel.benefitsState.collectAsState()

    when (val value = benefitsState.value) {
        is Resource.Loading -> Loading()
        is Resource.Success<List<BenefitModel>> -> BenefitsList(value.data)
        is Resource.Error -> { }
        else -> { }
    }

}

@Composable
fun BenefitsList(items: List<BenefitModel>) {
    LazyColumn {
        items(items.size, { items[it].id }) { index ->
            BenefitItem(item = items[index])
        }
    }
}

@Preview(showSystemUi = true)
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
    )
}