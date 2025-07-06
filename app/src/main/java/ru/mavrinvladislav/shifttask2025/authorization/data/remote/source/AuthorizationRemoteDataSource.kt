package ru.mavrinvladislav.shifttask2025.authorization.data.remote.source

import ru.mavrinvladislav.shifttask2025.core.common.remote.ServerResponse

interface AuthorizationRemoteDataSource {

    suspend fun signIn(
        phoneNumber: String,
        otpCode: String
    ): ServerResponse<String>

    suspend fun createOtp(
        phoneNumber: String
    ): ServerResponse<Double>
}