package ru.mavrinvladislav.shifttask2025.pizza

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import ru.mavrinvladislav.shifttask2025.pizza.pizza_catalog_screen.presentation.PizzaCatalogContent
import ru.mavrinvladislav.shifttask2025.pizza.pizza_details_screen.PizzaDetailsContent

@Composable
fun PizzaContent(component: PizzaComponent) {

    val stack by component.navStack.subscribeAsState()

    Children(
        stack = stack
    ) {
        when (val instance = it.instance) {
            is PizzaChild.Catalog -> {
                PizzaCatalogContent(instance.component)
            }

            is PizzaChild.Details -> {
                PizzaDetailsContent(instance.component)
            }
        }
    }
}