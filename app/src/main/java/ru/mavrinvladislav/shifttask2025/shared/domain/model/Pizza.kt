package ru.mavrinvladislav.shifttask2025.shared.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Pizza(
    val id: String,
    val name: String,
    val description: String,
    val sizes: List<Size>,
    val doughs: List<Dough>,
    val ingredients: List<Ingredient>,
    val toppings: List<Topping>,
    val calories: Int,
    val protein: String,
    val totalFat: String,
    val carbohydrates: String,
    val sodium: String,
    val allergens: List<String>,
    val isVegetarian: Boolean,
    val isGlutenFree: Boolean,
    val isNew: Boolean,
    val isHit: Boolean,
    val imgUrl: String,
) {
    fun getStartPrice(): Int {
        var res: Int = 0
        ingredients.forEach {
            res += it.price
        }
        res += sizes.first().price
        res += doughs.first().price
        return res
    }
}