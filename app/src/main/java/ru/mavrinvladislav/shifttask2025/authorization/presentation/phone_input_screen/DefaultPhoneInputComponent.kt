package ru.mavrinvladislav.shifttask2025.authorization.presentation.phone_input_screen

import android.util.Log
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.mavrinvladislav.shifttask2025.core.common.decompose.componentScope

class DefaultPhoneInputComponent @AssistedInject constructor(
    private val storeFactory: PhoneInputScreenStoreFactory,
    @Assisted("componentContext")
    componentContext: ComponentContext,
    @Assisted("onClose")
    onClose: () -> Unit,
    @Assisted("onSuccessfulCreateOtp")
    onSuccessfulCreateOtp: (String, Int) -> Unit
) : PhoneInputComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create() }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    is PhoneInputScreenStore.Label.Error -> {

                    }

                    is PhoneInputScreenStore.Label.Closed -> {
                        onClose()
                    }

                    is PhoneInputScreenStore.Label.SuccessfulCreateOtp -> {
                        onSuccessfulCreateOtp(
                            it.phoneNumber,
                            it.retryDelay
                        )
                    }

                }
            }
        }
    }

    override fun close() {
        store.accept(PhoneInputScreenStore.Intent.Close)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<PhoneInputScreenStore.State>
        get() = store.stateFlow

    override fun updatePhone(phoneNumber: String) {
        store.accept(PhoneInputScreenStore.Intent.UpdatePhone(phoneNumber))
    }

    override fun next() {
        store.accept(PhoneInputScreenStore.Intent.Next)
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext")
            componentContext: ComponentContext,
            @Assisted("onClose")
            onClose: () -> Unit,
            @Assisted("onSuccessfulCreateOtp")
            onSuccessfulCreateOtp: (String, Int) -> Unit
        ): DefaultPhoneInputComponent
    }
}