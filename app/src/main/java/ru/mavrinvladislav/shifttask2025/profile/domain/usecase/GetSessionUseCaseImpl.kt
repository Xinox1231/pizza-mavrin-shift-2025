package ru.mavrinvladislav.shifttask2025.profile.domain.usecase

import ru.mavrinvladislav.shifttask2025.profile.domain.model.User
import ru.mavrinvladislav.shifttask2025.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class GetSessionUseCaseImpl @Inject constructor(
    private val repository: ProfileRepository
) : GetSessionUseCase, suspend () -> User by repository::getSession