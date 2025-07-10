package ru.mavrinvladislav.shifttask2025.pizza.domain.repository.usecase

import ru.mavrinvladislav.shifttask2025.pizza.domain.repository.PizzaRepository
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Pizza
import javax.inject.Inject

class GetCatalogUseCaseImpl @Inject constructor(
    private val repository: PizzaRepository
) : GetCatalogUseCase, suspend () -> List<Pizza> by repository::loadCatalog