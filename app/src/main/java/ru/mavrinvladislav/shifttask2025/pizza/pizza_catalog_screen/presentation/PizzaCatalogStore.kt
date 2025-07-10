package ru.mavrinvladislav.shifttask2025.pizza.pizza_catalog_screen.presentation

import android.util.Log
import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.mavrinvladislav.shifttask2025.core.common.remote.RemoteConstants
import ru.mavrinvladislav.shifttask2025.pizza.domain.repository.usecase.GetCatalogUseCase
import ru.mavrinvladislav.shifttask2025.pizza.pizza_catalog_screen.presentation.PizzaListStore.Intent
import ru.mavrinvladislav.shifttask2025.pizza.pizza_catalog_screen.presentation.PizzaListStore.Label
import ru.mavrinvladislav.shifttask2025.pizza.pizza_catalog_screen.presentation.PizzaListStore.State
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Pizza
import javax.inject.Inject

interface PizzaListStore : Store<Intent, State, Label> {

    sealed interface Intent {

        data class ClickOnPizza(val pizza: Pizza) : Intent

        data object Reload : Intent
    }

    data class State(
        val catalogState: CatalogState
    ) {
        sealed interface CatalogState {

            data object Initial : CatalogState

            data object Loading : CatalogState

            data class Loaded(val list: List<Pizza>) : CatalogState

            data class Error(val reason: String) : CatalogState
        }
    }

    sealed interface Label {
        data class ClickedOnPizza(val pizza: Pizza) : Label
    }
}

class PizzaListStoreFactory @Inject constructor(
    private val getCatalogUseCase: GetCatalogUseCase,
    private val storeFactory: StoreFactory
) {

    fun create(): PizzaListStore =
        object : PizzaListStore, Store<Intent, State, Label> by storeFactory.create(
            name = "PizzaListStore",
            initialState = State(
                catalogState = State.CatalogState.Initial
            ),
            bootstrapper = BootstrapperImpl(),
            executorFactory = { ExecutorImpl() },
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data object StartCatalogLoading : Action
    }

    private sealed interface Msg {
        data object Loading : Msg

        data class Loaded(val list: List<Pizza>) : Msg

        data class Error(val reason: String) : Msg
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            dispatch(Action.StartCatalogLoading)
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {

        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            dispatch(Msg.Error(throwable.message ?: RemoteConstants.UNKNOWN_ERROR))
        }

        var catalogJob: Job? = null

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.ClickOnPizza -> {
                    publish(Label.ClickedOnPizza(intent.pizza))
                }

                is Intent.Reload -> {
                    getCatalog()
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                Action.StartCatalogLoading -> {
                    getCatalog()
                }
            }
        }

        private fun getCatalog() {
            dispatch(Msg.Loading)
            catalogJob?.cancel()
            catalogJob = scope.launch(coroutineExceptionHandler + SupervisorJob()) {
                val list = getCatalogUseCase()
                dispatch(Msg.Loaded(list))
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when (msg) {
                is Msg.Error -> {
                    copy(catalogState = State.CatalogState.Error(msg.reason))
                }

                is Msg.Loading -> {
                    copy(catalogState = State.CatalogState.Loading)
                }

                is Msg.Loaded -> {
                    copy(catalogState = State.CatalogState.Loaded(msg.list))
                }
            }
    }
}
