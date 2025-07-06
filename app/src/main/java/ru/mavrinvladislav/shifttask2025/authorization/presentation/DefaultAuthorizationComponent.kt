package ru.mavrinvladislav.shifttask2025.authorization.presentation

import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DefaultAuthorizationComponent @AssistedInject constructor(
    @Assisted("componentContext")
    componentContext: ComponentContext
) : AuthorizationComponent {

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext")
            componentContext: ComponentContext
        ): DefaultAuthorizationComponent
    }
}