package ru.mavrinvladislav.shifttask2025.authorization.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.mavrinvladislav.shifttask2025.authorization.data.remote.dto.SignInRequest
import ru.mavrinvladislav.shifttask2025.authorization.data.remote.dto.SignInResponse

interface AuthorizationService {

    @POST("users/signin")
    suspend fun signIn(
        @Body signInRequest: SignInRequest
    ): Response<SignInResponse>
}