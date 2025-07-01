package ru.mavrinvladislav.shifttask2025.authorization.data.remote.source

import ru.mavrinvladislav.shifttask2025.authorization.data.remote.AuthorizationService
import ru.mavrinvladislav.shifttask2025.authorization.data.remote.dto.SignInDto
import ru.mavrinvladislav.shifttask2025.authorization.data.remote.dto.SignInRequest
import ru.mavrinvladislav.shifttask2025.core.common.remote.RemoteConstants
import javax.inject.Inject

class AuthorizationRemoteDataSourceImpl @Inject constructor(
    private val authorizationService: AuthorizationService
) : AuthorizationRemoteDataSource {

    override suspend fun signIn(phoneNumber: String, otpCode: String): SignInDto {
        return try {
            val request = SignInRequest(
                phoneNumber,
                otpCode.toInt()
            )
            val response = authorizationService.signIn(request)

            if (!response.isSuccessful) {
                return SignInDto.Failure("HTTP error code: ${response.code()}")
            }

            val body = response.body()
                ?: return SignInDto.Failure("Response body is null")

            if (!body.success) {
                return SignInDto.Failure(body.reason ?: RemoteConstants.UNKNOWN_ERROR)
            }

            val token = body.token
            if (token.isNullOrEmpty()) {
                return SignInDto.Failure("Token is null or empty")
            }

            SignInDto.Success(token)

        } catch (e: Exception) {
            SignInDto.Failure(e.message ?: RemoteConstants.UNKNOWN_ERROR)
        }
    }
}
