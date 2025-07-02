package ru.mavrinvladislav.shifttask2025.authorization.data.remote.source

import ru.mavrinvladislav.shifttask2025.authorization.data.remote.AuthorizationService
import ru.mavrinvladislav.shifttask2025.authorization.data.remote.dto.CreateOtpRequest
import ru.mavrinvladislav.shifttask2025.authorization.data.remote.dto.SignInRequest
import ru.mavrinvladislav.shifttask2025.core.common.remote.handleResponse
import javax.inject.Inject

class AuthorizationRemoteDataSourceImpl @Inject constructor(
    private val authorizationService: AuthorizationService
) : AuthorizationRemoteDataSource {

    override suspend fun signIn(phoneNumber: String, otpCode: String): String {
        val request = SignInRequest(phoneNumber, otpCode.toInt())
        val response = authorizationService.signIn(request)

        return response.handleResponse(
            isSuccess = { it.success },
            getReason = { it.reason },
            extract = {
                it.token
            }
        )
    }

    override suspend fun createOtp(phoneNumber: String): Double {
        val request = CreateOtpRequest(phoneNumber)
        val response = authorizationService.createOtp(request)

        return response.handleResponse(
            isSuccess = { it.success },
            getReason = { it.reason },
            extract = {
                it.retryDelay
            }
        )
    }
}
