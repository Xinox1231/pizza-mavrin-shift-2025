package ru.mavrinvladislav.shifttask2025.shared.presentation

import android.content.Context
import ru.mavrinvladislav.shifttask2025.R
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Ingredient
import ru.mavrinvladislav.shifttask2025.shared.domain.model.IngredientType

private val ingredientTypeResMap = mapOf(
    IngredientType.PINEAPPLE to R.string.pizza_pineapple,
    IngredientType.MOZZARELLA to R.string.pizza_mozzarella,
    IngredientType.PEPERONI to R.string.pizza_peperoni,
    IngredientType.GREEN_PEPPER to R.string.pizza_green_pepper,
    IngredientType.MUSHROOMS to R.string.pizza_mushrooms,
    IngredientType.BASIL to R.string.pizza_basil,
    IngredientType.CHEDDAR to R.string.pizza_cheddar,
    IngredientType.PARMESAN to R.string.pizza_parmesan,
    IngredientType.FETA to R.string.pizza_feta,
    IngredientType.HAM to R.string.pizza_ham,
    IngredientType.PICKLE to R.string.pizza_pickle,
    IngredientType.TOMATO to R.string.pizza_tomato,
    IngredientType.BACON to R.string.pizza_bacon,
    IngredientType.ONION to R.string.pizza_onion,
    IngredientType.CHILE to R.string.pizza_chile,
    IngredientType.SHRIMPS to R.string.pizza_shrimps,
    IngredientType.CHICKEN_FILLET to R.string.pizza_chicken_fillet,
    IngredientType.MEATBALLS to R.string.pizza_meatballs
)

fun IngredientType.parseToString(context: Context): String {
    val resId = ingredientTypeResMap[this]
        ?: error("String resource for $this is missing!")
    return context.getString(resId)
}

fun List<Ingredient>.toLocalizedString(context: Context): String {
    return this.joinToString(", ") { ingredient ->
        ingredient.type.parseToString(context)
    }
}
