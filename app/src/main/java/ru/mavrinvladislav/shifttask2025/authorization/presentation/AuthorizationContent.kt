package ru.mavrinvladislav.shifttask2025.authorization.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import ru.mavrinvladislav.shifttask2025.authorization.presentation.otp_input_screen.OtpInputScreen
import ru.mavrinvladislav.shifttask2025.authorization.presentation.phone_input_screen.PhoneInputScreen

@Composable
fun AuthorizationContent(component: AuthorizationComponent) {

    val navigation by component.navStack.subscribeAsState()

    Children(
        stack = navigation
    ) {
        when (val instance = it.instance) {
            is AuthorizationChild.OtpInput -> {
                OtpInputScreen(instance.component)
            }

            is AuthorizationChild.PhoneInput -> {
                PhoneInputScreen(instance.component)
            }
        }
    }
}