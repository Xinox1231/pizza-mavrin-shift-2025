package ru.mavrinvladislav.shifttask2025.rootScreen.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.mavrinvladislav.shifttask2025.authorization.presentation.AuthorizationComponent
import ru.mavrinvladislav.shifttask2025.main_component.MainComponent
import ru.mavrinvladislav.shifttask2025.splash.presentation.SplashComponent

interface RootComponent {
    val childStack: Value<ChildStack<*, RootChild>>
}

sealed interface RootChild {

    data class Splash(val component: SplashComponent) : RootChild

    data class Authorization(val component: AuthorizationComponent) : RootChild

    data class Main(val component: MainComponent) : RootChild

}