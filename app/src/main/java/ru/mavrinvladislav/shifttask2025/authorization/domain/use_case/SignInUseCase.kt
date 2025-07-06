package ru.mavrinvladislav.shifttask2025.authorization.domain.use_case

import ru.mavrinvladislav.shifttask2025.core.common.util.Either

interface SignInUseCase {
    suspend operator fun invoke(
        phoneNumber: String,
        otpCode: String
    ): Either<Unit, String>
}