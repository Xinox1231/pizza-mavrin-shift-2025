package ru.mavrinvladislav.shifttask2025.main_component

import ru.mavrinvladislav.shifttask2025.R

sealed class MainTab(
    val iconResId: Int,
    val textResId: Int,
) {

    data object Pizza : MainTab(R.drawable.ic_pizza, R.string.pizza)
    data object Orders : MainTab(R.drawable.ic_time, R.string.orders)
    data object TrashCan : MainTab(R.drawable.ic_trash_can, R.string.trash_can)
    data object Profile : MainTab(R.drawable.ic_user, R.string.profile)
}
