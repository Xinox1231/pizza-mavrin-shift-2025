package ru.mavrinvladislav.shifttask2025.profile.domain.model

data class User(
    val phone: String,
    val firstName: String,
    val middleName: String,
    val lastName: String,
    val email: String,
    val city: String
)