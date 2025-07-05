package ru.mavrinvladislav.shifttask2025.authorization.presentation.otp_input_screen

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.mavrinvladislav.shifttask2025.authorization.presentation.phone_input_screen.PhoneInputScreenStore
import ru.mavrinvladislav.shifttask2025.core.common.decompose.componentScope

class DefaultOtpInputComponent @AssistedInject constructor(
    private val otpInputStoreFactory: OtpInputStoreFactory,
    @Assisted("phoneNumber")
    phoneNumber: String,
    @Assisted("retryDelay")
    retryDelay: Int,
    @Assisted("componentContext")
    componentContext: ComponentContext,
    @Assisted("onClose")
    onClose: () -> Unit,
) : OtpInputComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        otpInputStoreFactory.create(
            phoneNumber,
            retryDelay
        )
    }
    private val scope = componentScope()

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<OtpInputStore.State>
        get() = store.stateFlow

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    is OtpInputStore.Label.Close -> {
                        onClose()
                    }

                    is OtpInputStore.Label.SuccessfulSignIn -> {
                        _event.send(
                            OtpInputEvent.SuccessfulSignIn
                        )
                    }

                    is OtpInputStore.Label.ErrorSignIn -> {
                        _event.send(OtpInputEvent.ErrorSignIn(it.reason))
                    }

                    is OtpInputStore.Label.RequestOtpFailure -> {
                        _event.send(OtpInputEvent.ErrorRequestOtp(it.reason))
                    }

                    is OtpInputStore.Label.RequestOtpSucceed -> {
                        _event.send(OtpInputEvent.SuccessfulRequestOtp)
                    }

                    is OtpInputStore.Label.HideKeyboard -> {
                        _event.send(OtpInputEvent.HideKeyBoard)
                    }
                }
            }
        }
    }

    override fun requestOtp() {
        store.accept(OtpInputStore.Intent.RequestOtp)
    }

    private val _event = Channel<OtpInputEvent>(Channel.BUFFERED)
    override val event: Flow<OtpInputEvent>
        get() = _event.receiveAsFlow()

    override fun close() {
        store.accept(OtpInputStore.Intent.Close)
    }

    override fun updateOtp(otpCode: String) {
        store.accept(OtpInputStore.Intent.UpdateOtp(otpCode))
    }

    override fun signIn() {
        store.accept(OtpInputStore.Intent.SignIn)
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("phoneNumber")
            phoneNumber: String,
            @Assisted("retryDelay")
            retryDelay: Int,
            @Assisted("componentContext")
            componentContext: ComponentContext,
            @Assisted("onClose")
            onClose: () -> Unit,
        ): DefaultOtpInputComponent
    }
}
