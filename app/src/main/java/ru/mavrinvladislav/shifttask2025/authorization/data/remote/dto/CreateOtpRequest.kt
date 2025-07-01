package ru.mavrinvladislav.shifttask2025.authorization.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CreateOtpRequest(
    @SerializedName("phone")
    val phoneNumber: String,
)