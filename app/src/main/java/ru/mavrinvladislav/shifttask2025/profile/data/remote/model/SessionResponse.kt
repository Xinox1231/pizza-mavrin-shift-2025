package ru.mavrinvladislav.shifttask2025.profile.data.remote.model

import com.google.gson.annotations.SerializedName

data class SessionResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("reason")
    val reason: String,
    @SerializedName("user")
    val user: UserDto
)