package ru.mavrinvladislav.shifttask2025.authorization.data.remote.source

import ru.mavrinvladislav.shifttask2025.authorization.data.remote.dto.CreateOtpRequest
import ru.mavrinvladislav.shifttask2025.authorization.data.remote.dto.CreateOtpResponse
import ru.mavrinvladislav.shifttask2025.authorization.data.remote.dto.SignInResponse
import ru.mavrinvladislav.shifttask2025.core.common.remote.ServerResponse

interface AuthorizationRemoteDataSource {

    suspend fun signIn(
        phoneNumber: String,
        otpCode: String
    ): String

    suspend fun createOtp(
        phoneNumber: String
    ): Double
}