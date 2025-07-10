package ru.mavrinvladislav.shifttask2025.pizza

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.mavrinvladislav.shifttask2025.pizza.pizza_catalog_screen.presentation.PizzaCatalogComponent
import ru.mavrinvladislav.shifttask2025.pizza.pizza_details_screen.PizzaDetailsComponent

interface PizzaComponent {
    val navStack: Value<ChildStack<*, PizzaChild>>
}

sealed interface PizzaChild {

    data class Catalog(val component: PizzaCatalogComponent) : PizzaChild

    data class Details(val component: PizzaDetailsComponent) : PizzaChild
}