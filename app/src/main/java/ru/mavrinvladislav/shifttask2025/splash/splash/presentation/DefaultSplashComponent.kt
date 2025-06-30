package ru.mavrinvladislav.shifttask2025.splash.splash.presentation

import com.arkivanov.decompose.ComponentContext
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DefaultSplashComponent @AssistedInject constructor(
    @Assisted("onAuthorized")
    private val onAuthorized: () -> Unit,
    @Assisted("onUnAuthorized")
    private val onUnAuthorized: () -> Unit,
    @Assisted("componentContext")
    componentContext: ComponentContext,
) : SplashComponent, ComponentContext by componentContext {

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("onAuthorized")
            onAuthorized: () -> Unit,
            @Assisted("onUnAuthorized")
            onUnAuthorized: () -> Unit,
            @Assisted("componentContext")
            componentContext: ComponentContext
        ): DefaultSplashComponent
    }
}