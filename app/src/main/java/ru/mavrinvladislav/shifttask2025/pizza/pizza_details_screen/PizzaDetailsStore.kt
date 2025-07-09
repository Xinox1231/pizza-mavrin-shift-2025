package ru.mavrinvladislav.shifttask2025.pizza.pizza_details_screen

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import ru.mavrinvladislav.shifttask2025.pizza.pizza_details_screen.PizzaDetailsStore.Intent
import ru.mavrinvladislav.shifttask2025.pizza.pizza_details_screen.PizzaDetailsStore.Label
import ru.mavrinvladislav.shifttask2025.pizza.pizza_details_screen.PizzaDetailsStore.State
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Dough
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Pizza
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Size
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Topping
import javax.inject.Inject

interface PizzaDetailsStore : Store<Intent, State, Label> {

    sealed interface Intent {

        data object ClickBack : Intent

        data class UpdateSize(val size: Size) : Intent

        data class UpdateDough(val dough: Dough) : Intent

        data class UpdateTopping(val topping: Topping) : Intent

        data object AddToCart : Intent
    }

    data class State(
        val pizza: Pizza,
        val selectedSize: Size,
        val selectedDough: Dough,
        val selectedToppings: Set<Topping>
    )

    sealed interface Label {
        data object OnBackClicked : Label
    }
}

class PizzaDetailsStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory
) {

    fun create(pizza: Pizza): PizzaDetailsStore =
        object : PizzaDetailsStore, Store<Intent, State, Label> by storeFactory.create(
            name = "PizzaDetailsStore",
            initialState = State(
                pizza = pizza,
                selectedSize = pizza.sizes.first(),
                selectedDough = pizza.doughs.first(),
                selectedToppings = emptySet()
            ),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
    }

    private sealed interface Msg {
        data class UpdatedSize(val size: Size) : Msg

        data class UpdatedDough(val dough: Dough) : Msg

        data class UpdatedToppings(val toppings: Set<Topping>) : Msg
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
        }
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.UpdateDough -> {
                    dispatch(Msg.UpdatedDough(intent.dough))
                }

                is Intent.UpdateSize -> {
                    dispatch(Msg.UpdatedSize(intent.size))
                }

                is Intent.UpdateTopping -> {
                    val currentToppings = getState().selectedToppings
                    val topping = intent.topping
                    val newToppings = if (currentToppings.contains(topping)) {
                        currentToppings - topping
                    } else {
                        currentToppings + topping
                    }
                    dispatch(Msg.UpdatedToppings(newToppings))
                }

                is Intent.ClickBack -> {
                    publish(Label.OnBackClicked)
                }

                is Intent.AddToCart -> {
                    TODO()
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when (msg) {
                is Msg.UpdatedDough -> {
                    copy(selectedDough = msg.dough)
                }

                is Msg.UpdatedSize -> {
                    copy(selectedSize = msg.size)
                }

                is Msg.UpdatedToppings -> {
                    copy(selectedToppings = msg.toppings)
                }
            }
    }
}
