package ru.mavrinvladislav.shifttask2025.shared.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Size(
    val type: SizeType,
    val price: Int
)