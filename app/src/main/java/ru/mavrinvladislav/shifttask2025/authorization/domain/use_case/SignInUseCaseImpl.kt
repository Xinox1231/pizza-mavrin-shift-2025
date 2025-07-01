package ru.mavrinvladislav.shifttask2025.authorization.domain.use_case

import ru.mavrinvladislav.shifttask2025.authorization.domain.repository.AuthorizationRepository
import ru.mavrinvladislav.shifttask2025.core.common.util.Either
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(
    private val repository: AuthorizationRepository
) : SignInUseCase {
    override suspend fun invoke(phoneNumber: String, otpCode: String): Either<Unit, String> =
        repository.signIn(phoneNumber, otpCode)
}