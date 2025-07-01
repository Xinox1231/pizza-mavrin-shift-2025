package ru.mavrinvladislav.shifttask2025.authorization.data.remote.source

import ru.mavrinvladislav.shifttask2025.authorization.data.remote.AuthorizationService
import ru.mavrinvladislav.shifttask2025.authorization.data.remote.dto.CreateOtpRequest
import ru.mavrinvladislav.shifttask2025.authorization.data.remote.dto.SignInRequest
import ru.mavrinvladislav.shifttask2025.core.common.remote.RemoteConstants
import ru.mavrinvladislav.shifttask2025.core.common.remote.ServerResponse
import ru.mavrinvladislav.shifttask2025.core.common.remote.handleResponse
import javax.inject.Inject

class AuthorizationRemoteDataSourceImpl @Inject constructor(
    private val authorizationService: AuthorizationService
) : AuthorizationRemoteDataSource {

    override suspend fun signIn(phoneNumber: String, otpCode: String): ServerResponse<String> {
        val request = SignInRequest(phoneNumber, otpCode.toInt())
        return handleResponse(
            call = { authorizationService.signIn(request) },
            mapper = { body ->
                if (!body.success) {
                    ServerResponse.Failure(body.reason ?: RemoteConstants.UNKNOWN_ERROR)
                } else {
                    val token = body.token
                    if (token.isNullOrEmpty()) {
                        ServerResponse.Failure("Token is null or empty")
                    } else {
                        ServerResponse.Success(token)
                    }
                }
            }
        )
    }

    override suspend fun createOtp(phoneNumber: String): ServerResponse<Double> {
        val request = CreateOtpRequest(phoneNumber)
        return handleResponse(
            call = { authorizationService.createOtp(request) },
            mapper = { body ->
                if (!body.success) {
                    ServerResponse.Failure(body.reason ?: RemoteConstants.UNKNOWN_ERROR)
                } else {
                    val delay = body.retryDelay
                    if (delay == null) {
                        ServerResponse.Failure("RetryDelay is null")
                    } else {
                        ServerResponse.Success(delay)
                    }
                }
            }
        )
    }

}
