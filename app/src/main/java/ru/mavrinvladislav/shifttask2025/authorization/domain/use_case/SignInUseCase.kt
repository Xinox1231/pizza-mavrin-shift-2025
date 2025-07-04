package ru.mavrinvladislav.shifttask2025.authorization.domain.use_case

interface SignInUseCase {
    suspend operator fun invoke(
        phoneNumber: String,
        otpCode: String
    )
}