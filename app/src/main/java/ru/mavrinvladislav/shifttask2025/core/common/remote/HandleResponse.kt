package ru.mavrinvladislav.shifttask2025.core.common.remote

import retrofit2.Response

inline fun <T, R> handleResponse(
    call: () -> Response<T>,
    crossinline mapper: (T) -> ServerResponse<R>
): ServerResponse<R> {
    return try {
        val response = call()
        if (!response.isSuccessful) {
            return ServerResponse.Failure("HTTP error code: ${response.code()}")
        }

        val body = response.body()
            ?: return ServerResponse.Failure("Response body is null")

        mapper(body)
    } catch (e: Exception) {
        ServerResponse.Failure(e.message ?: RemoteConstants.UNKNOWN_ERROR)
    }
}
