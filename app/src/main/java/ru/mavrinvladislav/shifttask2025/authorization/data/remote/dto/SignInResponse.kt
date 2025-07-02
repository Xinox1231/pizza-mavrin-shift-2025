package ru.mavrinvladislav.shifttask2025.authorization.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("reason")
    val reason: String?,
    @SerializedName("token")
    val token: String?
)