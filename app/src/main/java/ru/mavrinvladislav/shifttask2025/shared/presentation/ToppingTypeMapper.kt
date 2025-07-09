package ru.mavrinvladislav.shifttask2025.shared.presentation

import android.content.Context
import ru.mavrinvladislav.shifttask2025.R
import ru.mavrinvladislav.shifttask2025.shared.domain.model.ToppingType

private val toppingTypeResMap = mapOf(
    ToppingType.PINEAPPLE to R.string.pizza_pineapple,
    ToppingType.MOZZARELLA to R.string.pizza_mozzarella,
    ToppingType.PEPERONI to R.string.pizza_peperoni,
    ToppingType.GREEN_PEPPER to R.string.pizza_green_pepper,
    ToppingType.MUSHROOMS to R.string.pizza_mushrooms,
    ToppingType.BASIL to R.string.pizza_basil,
    ToppingType.CHEDDAR to R.string.pizza_cheddar,
    ToppingType.PARMESAN to R.string.pizza_parmesan,
    ToppingType.FETA to R.string.pizza_feta,
    ToppingType.HAM to R.string.pizza_ham,
    ToppingType.PICKLE to R.string.pizza_pickle,
    ToppingType.TOMATO to R.string.pizza_tomato,
    ToppingType.BACON to R.string.pizza_bacon,
    ToppingType.ONION to R.string.pizza_onion,
    ToppingType.CHILE to R.string.pizza_chile,
    ToppingType.SHRIMPS to R.string.pizza_shrimps,
    ToppingType.CHICKEN_FILLET to R.string.pizza_chicken_fillet,
    ToppingType.MEATBALLS to R.string.pizza_meatballs
)

fun ToppingType.parseToString(context: Context): String {
    val resId = toppingTypeResMap[this]
        ?: error("String resource for $this is missing!")
    return context.getString(resId)
}