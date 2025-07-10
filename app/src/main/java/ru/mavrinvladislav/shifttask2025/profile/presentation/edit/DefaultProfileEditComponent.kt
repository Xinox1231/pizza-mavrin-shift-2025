package ru.mavrinvladislav.shifttask2025.profile.presentation.edit

import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DefaultProfileEditComponent @AssistedInject constructor(
    @Assisted("componentContext")
    componentContext: ComponentContext
) : ProfileEditComponent {

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("componentContext")
            componentContext: ComponentContext
        ): DefaultProfileEditComponent
    }
}