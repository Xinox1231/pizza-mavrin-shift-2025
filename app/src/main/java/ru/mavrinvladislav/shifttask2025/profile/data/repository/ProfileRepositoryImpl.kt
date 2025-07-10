package ru.mavrinvladislav.shifttask2025.profile.data.repository

import ru.mavrinvladislav.shifttask2025.profile.data.remote.datasource.ProfileRemoteDataSource
import ru.mavrinvladislav.shifttask2025.profile.data.toDomain
import ru.mavrinvladislav.shifttask2025.profile.domain.model.User
import ru.mavrinvladislav.shifttask2025.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val remoteProfileDataSource: ProfileRemoteDataSource
) : ProfileRepository {
    override suspend fun getSession(): User {
        val userDto = remoteProfileDataSource.getSession().toDomain()
        return userDto
    }

    override suspend fun updateProfile(user: User): User {
        TODO("Not yet implemented")
    }
}