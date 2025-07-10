package ru.mavrinvladislav.shifttask2025.pizza.data.remote.model

import com.google.gson.annotations.SerializedName

data class DoughDto(
    @SerializedName("type")
    val type: String,

    @SerializedName("price")
    val price: Int
)