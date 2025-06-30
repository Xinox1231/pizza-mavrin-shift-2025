package ru.mavrinvladislav.shifttask2025.rootScreen.presentation

import android.util.Log
import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import ru.mavrinvladislav.shifttask2025.authorization.presentation.AuthorizationContent
import ru.mavrinvladislav.shifttask2025.main_screen.MainContent
import ru.mavrinvladislav.shifttask2025.splash.splash.presentation.SplashContent

@Composable
fun RootContent(component: RootComponent) {

    Log.d("RootContent", "RootContent")

    val stack = component.childStack.subscribeAsState()

    when (val instance = stack.value.active.instance) {
        is RootChild.Authorization -> {
            AuthorizationContent(instance.component)
        }

        is RootChild.Main -> {
            MainContent(instance.component)
        }

        is RootChild.Splash -> {
            SplashContent(instance.component)
        }
    }
}