package ru.mavrinvladislav.shifttask2025.shared.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    val type: IngredientType,
    val price: Int,
    val imgUrl: String
)