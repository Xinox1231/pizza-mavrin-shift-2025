package ru.mavrinvladislav.shifttask2025.main_component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import ru.mavrinvladislav.shifttask2025.cart.presentation.CartContent
import ru.mavrinvladislav.shifttask2025.core.design_system.component.bottom_bar.MainBottomBar
import ru.mavrinvladislav.shifttask2025.core.design_system.theme.ShiftTheme
import ru.mavrinvladislav.shifttask2025.orders.presentation.OrdersContent
import ru.mavrinvladislav.shifttask2025.pizza.PizzaContent
import ru.mavrinvladislav.shifttask2025.profile.presentation.ProfileContent

@Composable
fun MainContent(component: MainComponent) {

    val navStack by component.navigationStack.subscribeAsState()

    Scaffold(
        bottomBar = {
            MainBottomBar(
                tabs = listOf(MainTab.Pizza, MainTab.Orders, MainTab.TrashCan, MainTab.Profile),
                selectedTab = navStack.active.instance.toTab(),
                onClick = {
                    component.onTabSelected(tab = it)
                }
            )
        }
    ) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            color = ShiftTheme.colors.backgroundPrimary
        ) {
            Children(
                stack = navStack,
                animation = stackAnimation(fade() + slide())
            ) {
                when (val instance = it.instance) {
                    is MainChild.Pizza -> PizzaContent(instance.component)
                    is MainChild.TrashCan -> CartContent(instance.component)
                    is MainChild.Orders -> OrdersContent(instance.component)
                    is MainChild.Profile -> ProfileContent(instance.component)
                }
            }
        }

    }
}