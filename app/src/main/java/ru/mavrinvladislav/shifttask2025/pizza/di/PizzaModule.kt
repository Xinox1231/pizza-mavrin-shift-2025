package ru.mavrinvladislav.shifttask2025.pizza.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.mavrinvladislav.shifttask2025.pizza.data.remote.PizzaService
import ru.mavrinvladislav.shifttask2025.pizza.data.remote.source.PizzaRemoteDataSource
import ru.mavrinvladislav.shifttask2025.pizza.data.remote.source.PizzaRemoteDataSourceImpl
import ru.mavrinvladislav.shifttask2025.pizza.data.repository.PizzaRepositoryImpl
import ru.mavrinvladislav.shifttask2025.pizza.domain.repository.PizzaRepository
import ru.mavrinvladislav.shifttask2025.pizza.domain.repository.usecase.GetCatalogUseCase
import ru.mavrinvladislav.shifttask2025.pizza.domain.repository.usecase.GetCatalogUseCaseImpl

@Module
interface PizzaModule {

    @Binds
    fun bindRemoteDataSource(impl: PizzaRemoteDataSourceImpl): PizzaRemoteDataSource

    @Binds
    fun bindPizzaRepository(impl: PizzaRepositoryImpl): PizzaRepository

    @Binds
    fun bindGetCatalogUseCase(impl: GetCatalogUseCaseImpl): GetCatalogUseCase

    companion object {

        @Provides
        fun providePizzaService(retrofit: Retrofit) = retrofit.create(PizzaService::class.java)
    }
}