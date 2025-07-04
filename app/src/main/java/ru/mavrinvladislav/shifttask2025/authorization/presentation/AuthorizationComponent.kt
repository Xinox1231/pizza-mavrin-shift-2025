package ru.mavrinvladislav.shifttask2025.authorization.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.mavrinvladislav.shifttask2025.authorization.presentation.otp_input_screen.OtpInputComponent
import ru.mavrinvladislav.shifttask2025.authorization.presentation.phone_input_screen.PhoneInputComponent

interface AuthorizationComponent {

    val navStack: Value<ChildStack<*, AuthorizationChild>>
}

sealed interface AuthorizationChild {

    data class PhoneInput(val component: PhoneInputComponent) : AuthorizationChild

    data class OtpInput(val component: OtpInputComponent) : AuthorizationChild

}