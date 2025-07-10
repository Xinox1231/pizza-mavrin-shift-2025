package ru.mavrinvladislav.shifttask2025.pizza.data.remote.model

import com.google.gson.annotations.SerializedName

data class CatalogResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("reason")
    val reason: String,

    @SerializedName("catalog")
    val catalog: List<PizzaDto>
)