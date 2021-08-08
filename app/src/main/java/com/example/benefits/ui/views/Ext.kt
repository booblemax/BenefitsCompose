package com.example.benefits.ui.views

import android.content.Context
import com.example.benefits.R
import com.example.benefits.domain.PlaceType

object EnumMapper {
    fun stringName(placeType: PlaceType, context: Context) = when(placeType) {
        PlaceType.SHOP -> context.getString(R.string.shop_name)
        PlaceType.CAFE -> context.getString(R.string.cafe_name)
        else -> context.getString(R.string.undefined_name)
    }
}

const val EMPTY: String = ""
