package ru.mavrinvladislav.shifttask2025.main_component

fun MainTab.toConfiguration() = when (this) {
    MainTab.Pizza -> DefaultMainComponent.MainConfig.Pizza
    MainTab.Orders -> DefaultMainComponent.MainConfig.Orders
    MainTab.TrashCan -> DefaultMainComponent.MainConfig.TrashCan
    MainTab.Profile -> DefaultMainComponent.MainConfig.Profile
}