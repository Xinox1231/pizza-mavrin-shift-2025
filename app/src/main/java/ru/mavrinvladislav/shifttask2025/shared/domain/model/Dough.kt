package ru.mavrinvladislav.shifttask2025.shared.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Dough(
    val type: DoughType,
    val price: Int
)