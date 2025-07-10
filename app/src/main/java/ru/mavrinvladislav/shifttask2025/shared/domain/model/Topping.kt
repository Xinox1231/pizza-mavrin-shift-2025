package ru.mavrinvladislav.shifttask2025.shared.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Topping(
    val type: ToppingType,
    val price: Int,
    val imgUrl: String,
)