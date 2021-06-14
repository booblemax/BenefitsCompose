package com.example.benefits.data

import android.content.Context
import com.example.benefits.domain.models.AddressModel
import com.example.benefits.domain.models.BenefitModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JsonDataExtractor {

    suspend fun loadDataFromJson(context: Context): List<BenefitModel> =
        withContext(Dispatchers.IO) {
            val gson = Gson()
            context.assets.open("db-seed.json").use {
                gson.fromJson(it.reader(), object : TypeToken<List<BenefitModel>>() {}.type)
            }

//            listOf(
//                BenefitModel(
//                    "1",
//                    "name1",
//                    "type1",
//                    AddressModel("city1", "street1"),
//                    "10-25%",
//                    "promo",
//                    "promo",
//                    "description of description of description of description of description of description of description of description of description of description",
//                    "icon1"
//                ),
//                BenefitModel(
//                    "2",
//                    "name2",
//                    "type1",
//                    AddressModel("city1", "street1"),
//                    "10-25%",
//                    "promo",
//                    "promo",
//                    "description of description of description of description of description of description of description of description of description of description",
//                    "icon1"
//                ),
//                BenefitModel(
//                    "3",
//                    "name3",
//                    "type1",
//                    AddressModel("city1", "street1"),
//                    "10-25%",
//                    "promo",
//                    "promo",
//                    "description of description of description of description of description of description of description of description of description of description",
//                    "icon1"
//                ),
//                BenefitModel(
//                    "4",
//                    "name4",
//                    "type1",
//                    AddressModel("city1", "street1"),
//                    "10-25%",
//                    "promo",
//                    "promo",
//                    "description of description of description of description of description of description of description of description of description of description",
//                    "icon1"
//                ),
//                BenefitModel(
//                    "5",
//                    "name5",
//                    "type1",
//                    AddressModel("city1", "street1"),
//                    "10-25%",
//                    "promo",
//                    "promo",
//                    "description of description of description of description of description of description of description of description of description of description",
//                    "icon1"
//                ),
//                BenefitModel(
//                    "6",
//                    "name6",
//                    "type1",
//                    AddressModel("city1", "street1"),
//                    "10-25%",
//                    "promo",
//                    "promo",
//                    "description of description of description of description of description of description of description of description of description of description",
//                    "icon1"
//                ),
//                BenefitModel(
//                    "7",
//                    "name7",
//                    "type1",
//                    AddressModel("city1", "street1"),
//                    "10-25%",
//                    "promo",
//                    "promo",
//                    "description of description of description of description of description of description of description of description of description of description",
//                    "icon1"
//                ),
//                BenefitModel(
//                    "8",
//                    "name8",
//                    "type1",
//                    AddressModel("city1", "street1"),
//                    "10-25%",
//                    "promo",
//                    "promo",
//                    "description of description of description of description of description of description of description of description of description of description",
//                    "icon1"
//                ),
//                BenefitModel(
//                    "9",
//                    "name9",
//                    "type1",
//                    AddressModel("city1", "street1"),
//                    "10-25%",
//                    "promo",
//                    "promo",
//                    "description of description of description of description of description of description of description of description of description of description",
//                    "icon1"
//                ),
//                BenefitModel(
//                    "10",
//                    "name10",
//                    "type1",
//                    AddressModel("city1", "street1"),
//                    "10-25%",
//                    "promo",
//                    "promo",
//                    "description of description of description of description of description of description of description of description of description of description",
//                    "icon1"
//                )
//            )

        }
}