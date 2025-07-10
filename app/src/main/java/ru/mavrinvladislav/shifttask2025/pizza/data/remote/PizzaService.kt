package ru.mavrinvladislav.shifttask2025.pizza.data.remote

import retrofit2.Response
import retrofit2.http.GET
import ru.mavrinvladislav.shifttask2025.pizza.data.remote.model.CatalogResponse

interface PizzaService {

    @GET("pizza/catalog")
    suspend fun loadCatalog(): Response<CatalogResponse>
}