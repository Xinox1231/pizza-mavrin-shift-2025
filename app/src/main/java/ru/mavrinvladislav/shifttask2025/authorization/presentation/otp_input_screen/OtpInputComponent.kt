package ru.mavrinvladislav.shifttask2025.authorization.presentation.otp_input_screen

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface OtpInputComponent {
    fun close()

    val event: Flow<OtpInputEvent>

    val model: StateFlow<OtpInputStore.State>

    fun requestOtp()

    fun updateOtp(otpCode: String)

    fun signIn()
}