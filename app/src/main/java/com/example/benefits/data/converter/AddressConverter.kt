package com.example.benefits.data.converter

import androidx.room.TypeConverter
import com.example.benefits.data.AddressDto
import com.example.benefits.domain.models.AddressModel

class AddressConverter {

    @TypeConverter
    fun from(model: AddressDto): String = with(model) { "$city,$street" }

    @TypeConverter
    fun to(model: String): AddressDto = model.split(',').run { AddressDto(this[0], this[1]) }
}