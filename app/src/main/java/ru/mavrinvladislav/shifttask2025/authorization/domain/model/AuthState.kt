package ru.mavrinvladislav.shifttask2025.authorization.domain.model

sealed class AuthState {
    data object Authorized: AuthState()
    data object UnAuthorized: AuthState()
}