package ru.mavrinvladislav.shifttask2025.authorization.data.remote.source

interface AuthorizationRemoteDataSource {

    suspend fun signIn(
        phoneNumber: String,
        otpCode: String
    ): String

    suspend fun createOtp(
        phoneNumber: String
    ): Long
}