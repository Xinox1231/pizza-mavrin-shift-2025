package ru.mavrinvladislav.shifttask2025.pizza.domain.repository

import ru.mavrinvladislav.shifttask2025.shared.domain.model.Pizza

interface PizzaRepository {

    suspend fun loadCatalog(): List<Pizza>
}