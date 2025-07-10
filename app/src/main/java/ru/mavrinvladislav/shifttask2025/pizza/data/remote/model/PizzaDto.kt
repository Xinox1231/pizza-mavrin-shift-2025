package ru.mavrinvladislav.shifttask2025.pizza.data.remote.model

import com.google.gson.annotations.SerializedName

data class PizzaDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("ingredients")
    val ingredients: List<IngredientDto>,

    @SerializedName("toppings")
    val toppings: List<ToppingDto>,

    @SerializedName("description")
    val description: String,

    @SerializedName("sizes")
    val sizes: List<SizeDto>,

    @SerializedName("doughs")
    val doughs: List<DoughDto>,

    @SerializedName("calories")
    val calories: Int,

    @SerializedName("protein")
    val protein: String,

    @SerializedName("totalFat")
    val totalFat: String,

    @SerializedName("carbohydrates")
    val carbohydrates: String,

    @SerializedName("sodium")
    val sodium: String,

    @SerializedName("allergens")
    val allergens: List<String>,

    @SerializedName("isVegetarian")
    val isVegetarian: Boolean,

    @SerializedName("isGlutenFree")
    val isGlutenFree: Boolean,

    @SerializedName("isNew")
    val isNew: Boolean,

    @SerializedName("isHit")
    val isHit: Boolean,

    @SerializedName("img")
    val img: String
)