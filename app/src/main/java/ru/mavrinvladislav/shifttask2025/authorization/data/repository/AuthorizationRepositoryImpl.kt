package ru.mavrinvladislav.shifttask2025.authorization.data.repository

import ru.mavrinvladislav.shifttask2025.authorization.data.remote.source.AuthorizationRemoteDataSource
import ru.mavrinvladislav.shifttask2025.authorization.domain.repository.AuthorizationRepository
import ru.mavrinvladislav.shifttask2025.shared.data.local.datasource.TokenLocalDataSource
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val remoteSource: AuthorizationRemoteDataSource,
    private val localSource: TokenLocalDataSource,
) : AuthorizationRepository {

    override suspend fun signIn(
        phoneNumber: String,
        otpCode: String
    ) {
        val result = remoteSource.signIn(phoneNumber, otpCode)
        localSource.saveAccessToken(result)
    }

    override suspend fun createOtp(phoneNumber: String): Int {
        val retryDelay = remoteSource.createOtp(phoneNumber)
        return TimeUnit.MILLISECONDS.toSeconds(retryDelay).toInt()
    }

    companion object {

    }
}