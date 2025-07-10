package ru.mavrinvladislav.shifttask2025.orders.presentation

import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DefaultOrdersComponent @AssistedInject constructor(
    @Assisted("componentContext")
    componentContext: ComponentContext
) : OrdersComponent, ComponentContext by componentContext{

    @AssistedFactory
    interface Factory{

        fun create(
            @Assisted("componentContext")
            componentContext: ComponentContext
        ): DefaultOrdersComponent
    }
}