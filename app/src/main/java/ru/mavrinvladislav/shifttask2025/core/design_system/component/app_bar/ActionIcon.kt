package ru.mavrinvladislav.shifttask2025.core.design_system.component.app_bar

data class ActionIcon(
    val iconResId: Int,
    val contentDescription: String? = null,
    val onClick: () -> Unit
)
