package ru.mavrinvladislav.shifttask2025.profile.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("phone")
    val phone: String,
    @SerializedName("firstname")
    val firstName: String,
    @SerializedName("middlename")
    val middleName: String,
    @SerializedName("lastname")
    val lastName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("city")
    val city: String
)