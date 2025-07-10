package ru.mavrinvladislav.shifttask2025.core.common.remote

import okhttp3.Interceptor
import okhttp3.Response
import ru.mavrinvladislav.shifttask2025.shared.data.local.datasource.TokenLocalDataSource
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenLocalDataSource: TokenLocalDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        val token = tokenLocalDataSource.getAccessToken()

        if (token != null && chain.request().header("authorization") != null) {
            requestBuilder.header("Authorization", "Bearer $token")
        }

        return chain.proceed(requestBuilder.build())
    }
}
