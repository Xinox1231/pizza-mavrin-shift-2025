package ru.mavrinvladislav.shifttask2025.authorization.domain.use_case

interface CreateOtpUseCase {
    suspend operator fun invoke(phoneNumber: String): Int
}