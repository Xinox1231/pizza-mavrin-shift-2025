package ru.mavrinvladislav.shifttask2025.authorization.domain.use_case

import kotlinx.coroutines.flow.Flow
import ru.mavrinvladislav.shifttask2025.authorization.domain.model.AuthState

interface IsAuthorizedUseCase {
    operator fun invoke(): Flow<AuthState>
}