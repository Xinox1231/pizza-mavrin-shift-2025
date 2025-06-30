package ru.mavrinvladislav.shifttask2025.main_screen

import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DefaultMainComponent @AssistedInject constructor(
    @Assisted("componentContext")
    componentContext: ComponentContext
) : MainComponent {

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("componentContext")
            componentContext: ComponentContext
        ): DefaultMainComponent
    }
}