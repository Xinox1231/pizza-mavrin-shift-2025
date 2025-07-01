package ru.mavrinvladislav.shifttask2025

import android.app.Application
import ru.mavrinvladislav.shifttask2025.core.common.di.DaggerApplicationComponent

class PizzaApp: Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}