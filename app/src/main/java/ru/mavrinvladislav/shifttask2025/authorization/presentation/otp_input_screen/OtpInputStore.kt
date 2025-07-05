package ru.mavrinvladislav.shifttask2025.authorization.presentation.otp_input_screen

import android.util.Log
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.mavrinvladislav.shifttask2025.authorization.domain.use_case.CreateOtpUseCase
import ru.mavrinvladislav.shifttask2025.authorization.domain.use_case.SignInUseCase
import ru.mavrinvladislav.shifttask2025.authorization.presentation.otp_input_screen.OtpInputStore.Intent
import ru.mavrinvladislav.shifttask2025.authorization.presentation.otp_input_screen.OtpInputStore.Label
import ru.mavrinvladislav.shifttask2025.authorization.presentation.otp_input_screen.OtpInputStore.State
import ru.mavrinvladislav.shifttask2025.core.common.EventBusController
import ru.mavrinvladislav.shifttask2025.core.common.remote.RemoteConstants
import ru.mavrinvladislav.shifttask2025.core.presentation.AppEvent
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

interface OtpInputStore : Store<Intent, State, Label> {

    sealed interface Intent {

        data object Close : Intent
        data class UpdateOtp(val otpCode: String) : Intent
        data object RequestOtp : Intent
        data object SignIn : Intent
    }

    data class State(
        val phoneNumber: String,
        val otpCode: String,
        val loadingState: LoadingState,
        val coolDownState: CoolDownState
    ) {
        sealed interface LoadingState {
            data object Initial : LoadingState
            data object Loading : LoadingState
        }

        sealed interface CoolDownState {
            data object Allowed : CoolDownState
            data class Waiting(val retryDelay: Int) : CoolDownState
        }
    }

    sealed interface Label {
        data object Close : Label
        data object SuccessfulSignIn : Label
        data object RequestOtpSucceed : Label

        data class RequestOtpFailure(val reason: String) : Label

        data class ErrorSignIn(val reason: String) : Label

        data object HideKeyboard : Label
    }
}

class OtpInputStoreFactory @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val createOtpUseCase: CreateOtpUseCase,
    private val eventBusController: EventBusController,
    private val storeFactory: StoreFactory
) {

    fun create(
        phoneNumber: String,
        coolDownDelay: Int,
    ): OtpInputStore =
        object : OtpInputStore, Store<Intent, State, Label> by storeFactory.create(
            name = "OtpInputStore",
            initialState = State(
                phoneNumber = phoneNumber,
                otpCode = EMPTY_STRING,
                loadingState = State.LoadingState.Initial,
                coolDownState = State.CoolDownState.Waiting(coolDownDelay)
            ),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data object TimerStarted : Action
    }

    private sealed interface Msg {
        data object LoadingStarted : Msg
        data object LoadingStopped : Msg
        data class OtpCodeUpdated(val newOtp: String) : Msg
        data class RetryDelayUpdated(val newRetryDelay: Int) : Msg
        data object RetryRequestAllowed : Msg
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            dispatch(Action.TimerStarted)
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.Close -> {
                    publish(Label.Close)
                }

                is Intent.SignIn -> {
                    val coroutineExceptionHandler =
                        CoroutineExceptionHandler { _, throwable ->
                            publish(
                                Label.ErrorSignIn(
                                    throwable.message ?: RemoteConstants.UNKNOWN_ERROR
                                )
                            )
                        }
                    scope.launch(coroutineExceptionHandler + SupervisorJob()) {
                        publish(Label.HideKeyboard)
                        dispatch(Msg.LoadingStarted)
                        val state = getState()
                        try {
                            signInUseCase(
                                phoneNumber = state.phoneNumber,
                                otpCode = state.otpCode
                            )
                            publish(Label.SuccessfulSignIn)
                            eventBusController.publishEvent(AppEvent.AUTHORIZE)
                        } finally {
                            dispatch(Msg.LoadingStopped)
                        }
                    }
                }

                is Intent.UpdateOtp -> {
                    dispatch(Msg.OtpCodeUpdated(intent.otpCode))
                }

                is Intent.RequestOtp -> {
                    val coroutineExceptionHandler =
                        CoroutineExceptionHandler { _, throwable ->
                            publish(
                                Label.RequestOtpFailure(
                                    throwable.message ?: RemoteConstants.UNKNOWN_ERROR
                                )
                            )
                        }
                    scope.launch(coroutineExceptionHandler + SupervisorJob()) {
                        try {
                            createOtpUseCase(getState().phoneNumber)
                            dispatch(Msg.LoadingStarted)
                            publish(Label.RequestOtpSucceed)
                            startTimer(getState)
                        } finally {
                            dispatch(Msg.LoadingStopped)
                        }
                    }
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                Action.TimerStarted -> {
                    startTimer(getState)
                }
            }
        }

        private fun startTimer(getState: () -> State) {
            val state = getState().coolDownState
            if (state is State.CoolDownState.Waiting) {
                scope.launch {
                    val retryDelay = state.retryDelay
                    for (i in retryDelay downTo 1) {
                        dispatch(Msg.RetryDelayUpdated(i))
                        delay(1.seconds)
                    }
                    dispatch(Msg.RetryRequestAllowed)
                }
            } else {
                return
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when (msg) {
                is Msg.OtpCodeUpdated -> {
                    copy(
                        otpCode = msg.newOtp
                    )
                }

                is Msg.LoadingStarted -> {
                    copy(
                        loadingState = State.LoadingState.Loading
                    )
                }

                is Msg.LoadingStopped -> {
                    copy(
                        loadingState = State.LoadingState.Initial
                    )
                }

                is Msg.RetryDelayUpdated -> {
                    copy(
                        coolDownState = State.CoolDownState.Waiting(msg.newRetryDelay)
                    )
                }

                is Msg.RetryRequestAllowed -> {
                    copy(
                        coolDownState = State.CoolDownState.Allowed
                    )
                }
            }
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
