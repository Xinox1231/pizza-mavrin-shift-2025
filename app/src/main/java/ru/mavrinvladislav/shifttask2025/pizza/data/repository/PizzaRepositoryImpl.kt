package ru.mavrinvladislav.shifttask2025.pizza.data.repository

import ru.mavrinvladislav.shifttask2025.pizza.data.remote.source.PizzaRemoteDataSource
import ru.mavrinvladislav.shifttask2025.pizza.data.toDomain
import ru.mavrinvladislav.shifttask2025.pizza.domain.repository.PizzaRepository
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Pizza
import javax.inject.Inject

class PizzaRepositoryImpl @Inject constructor(
    private val remoteDataSource: PizzaRemoteDataSource
) : PizzaRepository {
    override suspend fun loadCatalog(): List<Pizza> {
        val catalogDto = remoteDataSource.loadCatalog()
        val catalog = catalogDto.map { it.toDomain() }
        return catalog
    }
}