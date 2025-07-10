package ru.mavrinvladislav.shifttask2025.profile.presentation.user

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.mavrinvladislav.shifttask2025.core.common.EventBusController
import ru.mavrinvladislav.shifttask2025.core.common.remote.RemoteConstants
import ru.mavrinvladislav.shifttask2025.core.common.util.UnAuthorizedException
import ru.mavrinvladislav.shifttask2025.core.presentation.AppEvent
import ru.mavrinvladislav.shifttask2025.profile.domain.model.User
import ru.mavrinvladislav.shifttask2025.profile.domain.usecase.GetSessionUseCase
import ru.mavrinvladislav.shifttask2025.profile.presentation.user.ProfileUserStore.Intent
import ru.mavrinvladislav.shifttask2025.profile.presentation.user.ProfileUserStore.Label
import ru.mavrinvladislav.shifttask2025.profile.presentation.user.ProfileUserStore.State
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

interface ProfileUserStore : Store<Intent, State, Label> {

    sealed interface Intent {

        data object ClickOnUpdateData : Intent

        data object ClickOnLogOut : Intent

        data object Retry : Intent
    }

    data class State(
        val userState: UserState,
        val isLogOutBottomSheetOpened: Boolean = false
    ) {
        sealed interface UserState {

            data object Initial : UserState

            data object Loading : UserState

            data class Loaded(val user: User) : UserState

            data class Error(val reason: String) : UserState
        }
    }

    sealed interface Label {

        data object ClickedOnUpdateData : Label

        data object ClickedOnLogOut : Label
    }
}

class ProfileUserStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getSessionUseCase: GetSessionUseCase,
    private val eventBusController: EventBusController,
) {

    fun create(): ProfileUserStore =
        object : ProfileUserStore, Store<Intent, State, Label> by storeFactory.create(
            name = "ProfileUserStore",
            initialState = State(
                userState = State.UserState.Initial
            ),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data object StartLoading : Action
    }

    private sealed interface Msg {

        data object Loading : Msg

        data class Loaded(val user: User) : Msg

        data class Error(val reason: String) : Msg

    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            dispatch(Action.StartLoading)
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {

        var userLoadingDataJob: Job? = null

        private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            if (throwable is UnAuthorizedException) {
                scope.launch {
                    eventBusController.publishEvent(AppEvent.LOGOUT)
                    for (i in 5 downTo 1) {
                        delay(1.seconds)
                    }
                    reloadData()
                }
            } else {
                dispatch(Msg.Error(throwable.localizedMessage ?: RemoteConstants.UNKNOWN_ERROR))
            }
        }

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                Intent.ClickOnUpdateData -> {
                    publish(Label.ClickedOnUpdateData)
                }

                Intent.ClickOnLogOut -> {
                    publish(Label.ClickedOnLogOut)
                }

                Intent.Retry -> {
                    reloadData()
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                Action.StartLoading -> {
                    reloadData()
                }
            }
        }

        private fun reloadData() {
            dispatch(Msg.Loading)
            userLoadingDataJob?.cancel()
            userLoadingDataJob = scope.launch(coroutineExceptionHandler + SupervisorJob()) {
                val user = getSessionUseCase()
                dispatch(Msg.Loaded(user))
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when (msg) {

                is Msg.Loading -> {
                    copy(userState = State.UserState.Loading)
                }

                is Msg.Error -> {
                    copy(userState = State.UserState.Error(msg.reason))
                }

                is Msg.Loaded -> {
                    copy(userState = State.UserState.Loaded(msg.user))
                }
            }
    }
}
