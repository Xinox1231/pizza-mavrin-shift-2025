package ru.mavrinvladislav.shifttask2025.shared.presentation

import android.content.Context
import ru.mavrinvladislav.shifttask2025.R
import ru.mavrinvladislav.shifttask2025.shared.domain.model.DoughType

fun DoughType.parseToString(context: Context) = when (this) {
    DoughType.THIN -> context.getString(R.string.pizza_dough_thin)
    DoughType.THICK -> context.getString(R.string.pizza_dough_thick)

}