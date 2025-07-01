package ru.mavrinvladislav.shifttask2025.authorization.data.remote.dto

sealed class SignInDto {
    data class Success(val token: String) : SignInDto()
    data class Failure(val reason: String) : SignInDto()
}
