package ru.mavrinvladislav.shifttask2025.shared.presentation

import android.content.Context
import ru.mavrinvladislav.shifttask2025.R
import ru.mavrinvladislav.shifttask2025.shared.domain.model.SizeType

fun SizeType.parseToString(context: Context) = when (this) {
    SizeType.SMALL -> context.getString(R.string.pizza_size_small)
    SizeType.MEDIUM -> context.getString(R.string.pizza_size_medium)
    SizeType.LARGE -> context.getString(R.string.pizza_size_large)
}