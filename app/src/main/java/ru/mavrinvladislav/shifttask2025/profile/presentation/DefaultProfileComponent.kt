package ru.mavrinvladislav.shifttask2025.profile.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable
import ru.mavrinvladislav.shifttask2025.profile.presentation.edit.DefaultProfileEditComponent
import ru.mavrinvladislav.shifttask2025.profile.presentation.user.DefaultProfileUserComponent

class DefaultProfileComponent @AssistedInject constructor(
    private val profileUserComponentFactory: DefaultProfileUserComponent.Factory,
    private val profileEditComponentFactory: DefaultProfileEditComponent.Factory,
    @Assisted("componentContext")
    componentContext: ComponentContext
) : ProfileComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<ProfileConfig>()

    override val navStack: Value<ChildStack<*, ProfileChild>> = childStack(
        source = navigation,
        serializer = ProfileConfig.serializer(),
        initialConfiguration = ProfileConfig.ProfileUser,
        childFactory = ::child
    )

    fun child(
        config: ProfileConfig,
        componentContext: ComponentContext
    ): ProfileChild = when (config) {
        ProfileConfig.ProfileEdit -> {
            val component = profileEditComponentFactory.create(
                componentContext = componentContext
            )
            ProfileChild.ProfileEdit(component)
        }

        ProfileConfig.ProfileUser -> {
            val component = profileUserComponentFactory.create(
                componentContext = componentContext
            )
            ProfileChild.ProfileUser(component)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext")
            componentContext: ComponentContext
        ): DefaultProfileComponent
    }

    @Serializable
    sealed interface ProfileConfig {

        @Serializable
        data object ProfileUser : ProfileConfig

        @Serializable
        data object ProfileEdit : ProfileConfig
    }
}