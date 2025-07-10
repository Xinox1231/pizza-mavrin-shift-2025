package ru.mavrinvladislav.shifttask2025.pizza.data.remote.source

import ru.mavrinvladislav.shifttask2025.core.common.remote.handleResponse
import ru.mavrinvladislav.shifttask2025.pizza.data.remote.PizzaService
import ru.mavrinvladislav.shifttask2025.pizza.data.remote.model.PizzaDto
import javax.inject.Inject

class PizzaRemoteDataSourceImpl @Inject constructor(
    private val pizzaService: PizzaService
) : PizzaRemoteDataSource {
    override suspend fun loadCatalog(): List<PizzaDto> {
        val catalog = pizzaService.loadCatalog().handleResponse(
            isSuccess = { it.success },
            getReason = { it.reason },
            extract = {
                it.catalog
            }
        )
        return catalog
    }
}