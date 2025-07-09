package ru.mavrinvladislav.shifttask2025.pizza.data.remote.model

import com.google.gson.annotations.SerializedName

data class SizeDto(
    @SerializedName("type")
    val type: String,

    @SerializedName("price")
    val price: Int
)