package com.example.benefits.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JsonDataExtractor {

    suspend fun loadDataFromJson(): List<BenefitDto> =
        withContext(Dispatchers.IO) {
            listOf(
                BenefitDto("1", "name1", "type1", AddressDto("city1", "street1"), "", "","", "icon1"),
                BenefitDto("2", "name2", "type1", AddressDto("city1", "street1"), "", "","", "icon1"),
                BenefitDto("3", "name3", "type1", AddressDto("city1", "street1"), "", "","", "icon1"),
                BenefitDto("4", "name4", "type1", AddressDto("city1", "street1"), "", "","", "icon1"),
                BenefitDto("5", "name5", "type1", AddressDto("city1", "street1"), "", "","", "icon1"),
                BenefitDto("6", "name6", "type1", AddressDto("city1", "street1"), "", "","", "icon1"),
                BenefitDto("7", "name7", "type1", AddressDto("city1", "street1"), "", "","", "icon1"),
                BenefitDto("8", "name8", "type1", AddressDto("city1", "street1"), "", "","", "icon1"),
                BenefitDto("9", "name9", "type1", AddressDto("city1", "street1"), "", "", "","icon1"),
                BenefitDto("10", "name10", "type1", AddressDto("city1", "street1"), "", "", "", "icon1")
            )
        }
}