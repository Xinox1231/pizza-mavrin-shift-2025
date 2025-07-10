package ru.mavrinvladislav.shifttask2025.profile.domain.usecase

import ru.mavrinvladislav.shifttask2025.profile.domain.model.User

interface GetSessionUseCase {
    suspend operator fun invoke(): User
}