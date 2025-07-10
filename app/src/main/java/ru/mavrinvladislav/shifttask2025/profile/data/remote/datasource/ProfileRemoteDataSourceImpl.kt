package ru.mavrinvladislav.shifttask2025.profile.data.remote.datasource

import ru.mavrinvladislav.shifttask2025.core.common.remote.handleResponse
import ru.mavrinvladislav.shifttask2025.profile.data.remote.ProfileService
import ru.mavrinvladislav.shifttask2025.profile.data.remote.model.UserDto
import ru.mavrinvladislav.shifttask2025.profile.domain.model.User
import javax.inject.Inject

class ProfileRemoteDataSourceImpl @Inject constructor(
    private val profileService: ProfileService
) : ProfileRemoteDataSource {
    override suspend fun getSession(): UserDto {
        val response = profileService.getSession()
        val userDto = response.handleResponse(
            isSuccess = { it.success },
            getReason = { it.reason },
            extract = { it.user }
        )
        return userDto
    }

    override suspend fun updateProfile(user: User): UserDto {
        TODO()
    }
}