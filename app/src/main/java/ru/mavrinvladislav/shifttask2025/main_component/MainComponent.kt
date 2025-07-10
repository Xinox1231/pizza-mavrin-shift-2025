package ru.mavrinvladislav.shifttask2025.main_component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.mavrinvladislav.shifttask2025.cart.presentation.CartComponent
import ru.mavrinvladislav.shifttask2025.orders.presentation.OrdersComponent
import ru.mavrinvladislav.shifttask2025.pizza.PizzaComponent
import ru.mavrinvladislav.shifttask2025.profile.presentation.ProfileComponent

interface MainComponent {

    val navigationStack: Value<ChildStack<*, MainChild>>

    fun onTabSelected(tab: MainTab)
}

sealed interface MainChild {

    data class Pizza(val component: PizzaComponent) : MainChild

    data class Orders(val component: OrdersComponent) : MainChild

    data class TrashCan(val component: CartComponent) : MainChild

    data class Profile(val component: ProfileComponent) : MainChild
}