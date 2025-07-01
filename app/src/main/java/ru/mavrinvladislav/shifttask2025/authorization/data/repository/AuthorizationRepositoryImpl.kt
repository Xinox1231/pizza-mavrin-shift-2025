package ru.mavrinvladislav.shifttask2025.authorization.data.repository

import android.util.Log
import ru.mavrinvladislav.shifttask2025.shared.data.local.datasource.TokenLocalDataSource
import ru.mavrinvladislav.shifttask2025.authorization.data.remote.source.AuthorizationRemoteDataSource
import ru.mavrinvladislav.shifttask2025.authorization.data.remote.dto.SignInDto
import ru.mavrinvladislav.shifttask2025.authorization.domain.repository.AuthorizationRepository
import ru.mavrinvladislav.shifttask2025.core.common.util.Either
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val remoteSource: AuthorizationRemoteDataSource,
    private val localSource: TokenLocalDataSource,
) : AuthorizationRepository {

    override suspend fun signIn(
        phoneNumber: String,
        otpCode: String
    ): Either<Unit, String> {
        Log.d("AuthorizationRepository","Invoked")

        return when (val result = remoteSource.signIn(phoneNumber, otpCode)) {
            is SignInDto.Failure -> {
                Either.Failure(result.reason)
            }

            is SignInDto.Success -> {
                when (val saveResult = localSource.saveAccessToken(result.token)) {
                    is Either.Failure -> {
                        Either.Failure(saveResult.value)
                    }

                    is Either.Success -> {
                        Either.Success(Unit)
                    }
                }
            }
        }
    }
}
