package ru.mavrinvladislav.shifttask2025.profile.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import ru.mavrinvladislav.shifttask2025.core.common.remote.RequiresAuth
import ru.mavrinvladislav.shifttask2025.profile.data.remote.model.SessionResponse
import ru.mavrinvladislav.shifttask2025.profile.data.remote.model.UserDto

interface ProfileService {

    @RequiresAuth
    @GET("users/session")
    suspend fun getSession(): Response<SessionResponse>

    @RequiresAuth
    @PATCH("users/profile")
    fun updateProfile(userDto: UserDto): Response<SessionResponse>
}