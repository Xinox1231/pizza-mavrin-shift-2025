package ru.mavrinvladislav.shifttask2025.authorization.presentation.phone_input_screen

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.mavrinvladislav.shifttask2025.authorization.domain.use_case.CreateOtpUseCase
import ru.mavrinvladislav.shifttask2025.authorization.presentation.phone_input_screen.PhoneInputScreenStore.Intent
import ru.mavrinvladislav.shifttask2025.authorization.presentation.phone_input_screen.PhoneInputScreenStore.Label
import ru.mavrinvladislav.shifttask2025.authorization.presentation.phone_input_screen.PhoneInputScreenStore.State
import ru.mavrinvladislav.shifttask2025.core.common.remote.RemoteConstants
import javax.inject.Inject

interface PhoneInputScreenStore : Store<Intent, State, Label> {

    sealed interface Intent {

        data object Close : Intent
        data class UpdatePhone(val phoneNumber: String) : Intent
        data object Next : Intent
    }

    data class State(
        val phoneNumber: String,
        val isButtonEnabled: Boolean,
        val requestState: RequestState
    ) {
        sealed interface RequestState {
            data object Initial : RequestState
            data object Loading : RequestState
        }
    }

    sealed interface Label {
        data class SuccessfulCreateOtp(
            val phoneNumber: String,
            val retryDelay: Int
        ) : Label

        data class Error(val message: String) : Label
        data object Closed : Label
    }
}

class PhoneInputScreenStoreFactory @Inject constructor(
    private val createOtpUseCase: CreateOtpUseCase,
    private val storeFactory: StoreFactory
) {

    fun create(): PhoneInputScreenStore =
        object : PhoneInputScreenStore, Store<Intent, State, Label> by storeFactory.create(
            name = "PhoneInputScreenStore",
            initialState = State(
                phoneNumber = EMPTY_STRING,
                isButtonEnabled = true,
                requestState = State.RequestState.Initial
            ),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
    }

    private sealed interface Msg {
        data class UpdatedPhone(val phoneNumber: String) : Msg

        data object StartLoading : Msg
        data object EndLoading : Msg
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {

        private var job: Job? = null

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.Close -> {
                    publish(Label.Closed)
                }

                is Intent.Next -> {
                    if (getState().requestState == State.RequestState.Loading) {
                        // Уже идёт запрос -> игнорируем нажатие
                        return
                    }

                    val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
                        publish(Label.Error(throwable.message ?: RemoteConstants.UNKNOWN_ERROR))
                    }

                    scope.launch(coroutineExceptionHandler + SupervisorJob()) {
                        dispatch(Msg.StartLoading)
                        try {
                            val phoneNumber = getState().phoneNumber
                            val retryDelay = createOtpUseCase(phoneNumber)
                            publish(
                                Label.SuccessfulCreateOtp(
                                    phoneNumber,
                                    retryDelay
                                )
                            )
                        } finally {
                            dispatch(Msg.EndLoading)
                        }
                    }
                }

                is Intent.UpdatePhone -> {
                    dispatch(Msg.UpdatedPhone(intent.phoneNumber))
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when (msg) {
                is Msg.UpdatedPhone -> {
                    copy(
                        phoneNumber = msg.phoneNumber
                    )
                }

                is Msg.StartLoading -> {
                    copy(
                        isButtonEnabled = false,
                        requestState = State.RequestState.Loading
                    )
                }

                is Msg.EndLoading -> {
                    copy(
                        isButtonEnabled = true,
                        requestState = State.RequestState.Initial
                    )
                }
            }
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}
