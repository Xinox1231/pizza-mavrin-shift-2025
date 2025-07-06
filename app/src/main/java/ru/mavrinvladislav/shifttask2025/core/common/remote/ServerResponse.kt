package ru.mavrinvladislav.shifttask2025.core.common.remote

sealed class ServerResponse<out T> {
    data class Success<out T>(val data: T) : ServerResponse<T>()
    data class Failure(val reason: String) : ServerResponse<Nothing>()
}
