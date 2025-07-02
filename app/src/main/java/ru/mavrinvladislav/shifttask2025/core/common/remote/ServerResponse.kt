package ru.mavrinvladislav.shifttask2025.core.common.remote

data class ServerResponse<T>(
    val success: Boolean,
    val reason: String?,
    val data: T? // должен быть nullable, чтобы корректно обрабатывать
)
