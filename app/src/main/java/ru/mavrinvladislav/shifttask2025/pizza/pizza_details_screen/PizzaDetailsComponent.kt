package ru.mavrinvladislav.shifttask2025.pizza.pizza_details_screen

import kotlinx.coroutines.flow.StateFlow
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Size
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Topping

interface PizzaDetailsComponent {

    val model: StateFlow<PizzaDetailsStore.State>

    fun onBackClick()

    fun updateTopping(topping: Topping)

    fun updateSize(size: Size)

    fun addToCart()
}