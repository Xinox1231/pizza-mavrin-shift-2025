package ru.mavrinvladislav.shifttask2025.authorization.domain.use_case

import ru.mavrinvladislav.shifttask2025.authorization.domain.repository.AuthorizationRepository
import javax.inject.Inject

class CreateOtpUseCaseImpl @Inject constructor(
    private val repository: AuthorizationRepository
) : CreateOtpUseCase {
    override suspend fun invoke(phoneNumber: String) = repository.createOtp(phoneNumber)
}