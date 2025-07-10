package ru.mavrinvladislav.shifttask2025.profile.domain.repository

import ru.mavrinvladislav.shifttask2025.profile.domain.model.User

interface ProfileRepository {

    suspend fun getSession(): User

    suspend fun updateProfile(user: User): User
}