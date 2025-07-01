package ru.mavrinvladislav.shifttask2025.shared.data.local.model

interface TokenStorage {

    fun saveAccessToken(accessToken: String)

    fun getAccessToken(): String?

    fun deleteAccessToken()
}