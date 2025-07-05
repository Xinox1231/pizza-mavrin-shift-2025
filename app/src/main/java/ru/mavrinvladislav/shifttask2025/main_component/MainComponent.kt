package ru.mavrinvladislav.shifttask2025.main_component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.mavrinvladislav.shifttask2025.orders.OrdersComponent
import ru.mavrinvladislav.shifttask2025.pizza.PizzaComponent
import ru.mavrinvladislav.shifttask2025.profile.ProfileComponent
import ru.mavrinvladislav.shifttask2025.trash_can.TrashCanComponent

interface MainComponent {

    val navigationStack: Value<ChildStack<*, MainChild>>

    fun onTabSelected(tab: MainTab)
}

sealed interface MainChild {

    data class Pizza(val component: PizzaComponent) : MainChild

    data class Orders(val component: OrdersComponent) : MainChild

    data class TrashCan(val component: TrashCanComponent) : MainChild

    data class Profile(val component: ProfileComponent) : MainChild
}