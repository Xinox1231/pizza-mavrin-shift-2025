package ru.mavrinvladislav.shifttask2025.authorization.presentation.otp_input_screen

sealed interface OtpInputEvent {

    data class ErrorSignIn(val reason: String) : OtpInputEvent
    data object SuccessfulSignIn : OtpInputEvent
    data class ErrorRequestOtp(val reason: String) : OtpInputEvent
    data object SuccessfulRequestOtp : OtpInputEvent
    data object HideKeyBoard : OtpInputEvent

}
