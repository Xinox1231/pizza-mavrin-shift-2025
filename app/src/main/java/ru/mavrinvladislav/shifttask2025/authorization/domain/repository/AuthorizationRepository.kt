package ru.mavrinvladislav.shifttask2025.authorization.domain.repository

import ru.mavrinvladislav.shifttask2025.core.common.util.Either

interface AuthorizationRepository {

    suspend fun signIn(
        phoneNumber: String,
        otpCode: String
    )

    suspend fun createOtp(
        phoneNumber: String
    ): Int
}