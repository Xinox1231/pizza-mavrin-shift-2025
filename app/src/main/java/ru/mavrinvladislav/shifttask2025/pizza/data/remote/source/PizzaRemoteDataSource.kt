package ru.mavrinvladislav.shifttask2025.pizza.data.remote.source

import ru.mavrinvladislav.shifttask2025.pizza.data.remote.model.PizzaDto

interface PizzaRemoteDataSource {
    suspend fun loadCatalog(): List<PizzaDto>
}