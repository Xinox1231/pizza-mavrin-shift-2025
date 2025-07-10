package ru.mavrinvladislav.shifttask2025.shared.data.local.datasource

interface TokenLocalDataSource {

    fun saveAccessToken(token: String)

    fun getAccessToken(): String?
}