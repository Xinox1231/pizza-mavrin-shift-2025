package ru.mavrinvladislav.shifttask2025.profile.data

import ru.mavrinvladislav.shifttask2025.profile.data.remote.model.UserDto
import ru.mavrinvladislav.shifttask2025.profile.domain.model.User

fun UserDto.toDomain() = User(
    phone = phone,
    firstName = firstName,
    middleName = middleName,
    lastName = lastName,
    email = email,
    city = city
)