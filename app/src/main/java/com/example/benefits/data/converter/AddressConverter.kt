package com.example.benefits.data.converter

import androidx.room.TypeConverter
import com.example.benefits.domain.models.AddressModel

class AddressConverter {

    @TypeConverter
    fun from(model: AddressModel): String = with(model) { "$city,$street" }

    @TypeConverter
    fun to(model: String): AddressModel = model.split(',').run { AddressModel(this[0], this[1]) }
}