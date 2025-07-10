package ru.mavrinvladislav.shifttask2025.pizza.domain.repository.usecase

import ru.mavrinvladislav.shifttask2025.shared.domain.model.Pizza

interface GetCatalogUseCase {
    suspend operator fun invoke(): List<Pizza>
}