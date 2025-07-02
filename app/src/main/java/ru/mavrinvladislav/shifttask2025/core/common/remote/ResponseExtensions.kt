package ru.mavrinvladislav.shifttask2025.core.common.remote

import retrofit2.HttpException
import retrofit2.Response
import ru.mavrinvladislav.shifttask2025.core.common.util.AuthorizationException

inline fun <T, R> Response<T>.handleResponse(
    crossinline isSuccess: (T) -> Boolean,
    crossinline getReason: (T) -> String,
    crossinline extract: (T) -> R
): R {
    if (!isSuccessful) throw HttpException(this)

    val body = body() ?: throw NullPointerException("Response body is null")

    if (!isSuccess(body)) {
        val reason = getReason(body)
        throw AuthorizationException(reason)
    }

    return extract(body)
}
