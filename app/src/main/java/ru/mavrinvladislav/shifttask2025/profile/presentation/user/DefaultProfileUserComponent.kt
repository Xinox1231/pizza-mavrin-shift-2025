package ru.mavrinvladislav.shifttask2025.profile.presentation.user

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.mavrinvladislav.shifttask2025.core.common.decompose.componentScope

class DefaultProfileUserComponent @AssistedInject constructor(
    private val storeFactory: ProfileUserStoreFactory,
    @Assisted("componentContext")
    componentContext: ComponentContext,
) : ProfileUserComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create() }
    private val scope = componentScope()

    init {
        store.labels.onEach {
            when (it) {
                ProfileUserStore.Label.ClickedOnLogOut -> {}
                ProfileUserStore.Label.ClickedOnUpdateData -> {}
            }
        }.launchIn(scope)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<ProfileUserStore.State> = store.stateFlow

    override fun clickOnUpdateData() {
        store.accept(ProfileUserStore.Intent.ClickOnUpdateData)
    }

    override fun clickOnLogOut() {
        store.accept(ProfileUserStore.Intent.ClickOnLogOut)
    }

    override fun retry() {
        store.accept(ProfileUserStore.Intent.Retry)
    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("componentContext")
            componentContext: ComponentContext
        ): DefaultProfileUserComponent
    }
}