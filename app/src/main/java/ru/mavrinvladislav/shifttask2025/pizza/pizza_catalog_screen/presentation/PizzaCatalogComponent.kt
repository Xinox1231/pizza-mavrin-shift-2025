package ru.mavrinvladislav.shifttask2025.pizza.pizza_catalog_screen.presentation

import kotlinx.coroutines.flow.StateFlow
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Pizza

interface PizzaCatalogComponent {

    val model: StateFlow<PizzaListStore.State>

    fun retryRequest()

    fun clickOnPizza(pizza: Pizza)
}
