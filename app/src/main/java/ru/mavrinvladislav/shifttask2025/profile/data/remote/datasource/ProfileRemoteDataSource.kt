package ru.mavrinvladislav.shifttask2025.profile.data.remote.datasource

import ru.mavrinvladislav.shifttask2025.profile.data.remote.model.UserDto
import ru.mavrinvladislav.shifttask2025.profile.domain.model.User

interface ProfileRemoteDataSource {

    suspend fun getSession(): UserDto

    suspend fun updateProfile(user: User): UserDto
}