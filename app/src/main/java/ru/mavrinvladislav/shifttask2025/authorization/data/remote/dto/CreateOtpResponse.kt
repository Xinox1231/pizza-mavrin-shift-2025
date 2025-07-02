package ru.mavrinvladislav.shifttask2025.authorization.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CreateOtpResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("reason")
    val reason: String,
    @SerializedName("retryDelay")
    val retryDelay: Double
)