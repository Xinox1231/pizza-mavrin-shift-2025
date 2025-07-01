package ru.mavrinvladislav.shifttask2025.authorization.data.remote.source

import ru.mavrinvladislav.shifttask2025.authorization.data.remote.dto.SignInDto

interface AuthorizationRemoteDataSource {

    suspend fun signIn(
        phoneNumber: String,
        otpCode: String
    ): SignInDto
}