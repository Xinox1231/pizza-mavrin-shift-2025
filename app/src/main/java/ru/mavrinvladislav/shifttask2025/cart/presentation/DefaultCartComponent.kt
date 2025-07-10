package ru.mavrinvladislav.shifttask2025.cart.presentation

import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DefaultCartComponent @AssistedInject constructor(
    @Assisted("componentContext")
    componentContext: ComponentContext
) : CartComponent, ComponentContext by componentContext{

    @AssistedFactory
    interface Factory{

        fun create(
            @Assisted("componentContext")
            componentContext: ComponentContext
        ): DefaultCartComponent
    }
}