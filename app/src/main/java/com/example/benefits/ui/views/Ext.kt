package com.example.benefits.ui.views

import android.content.Context
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.benefits.R
import com.example.benefits.domain.PlaceType
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

object EnumMapper {
    fun stringName(placeType: PlaceType, context: Context) = when(placeType) {
        PlaceType.SHOP -> context.getString(R.string.shop_name)
        PlaceType.CAFE -> context.getString(R.string.cafe_name)
        else -> context.getString(R.string.undefined_name)
    }
}

const val EMPTY: String = ""

fun Modifier.loadingShimmer(isLoading: Boolean) =
    this.placeholder(
        isLoading,
        Color.LightGray,
        RoundedCornerShape(12.dp),
        highlight = PlaceholderHighlight.shimmer(Color.Gray)
    )

