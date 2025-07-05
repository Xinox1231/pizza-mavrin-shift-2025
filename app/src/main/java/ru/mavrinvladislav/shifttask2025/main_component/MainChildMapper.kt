package ru.mavrinvladislav.shifttask2025.main_component

fun MainChild.toTab() = when (this) {
    is MainChild.Pizza    -> MainTab.Pizza
    is MainChild.TrashCan -> MainTab.TrashCan
    is MainChild.Orders   -> MainTab.Orders
    is MainChild.Profile  -> MainTab.Profile
}
