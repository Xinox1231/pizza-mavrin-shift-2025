package ru.mavrinvladislav.shifttask2025.authorization.presentation.phone_input_screen

import kotlinx.coroutines.flow.StateFlow

interface PhoneInputComponent {

    fun close()

    val model: StateFlow<PhoneInputScreenStore.State>

    fun updatePhone(phoneNumber: String)

    fun next()
}