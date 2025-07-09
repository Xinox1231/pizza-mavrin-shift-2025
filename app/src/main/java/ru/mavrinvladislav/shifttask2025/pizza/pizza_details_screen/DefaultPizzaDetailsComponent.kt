@file:OptIn(ExperimentalCoroutinesApi::class)

package ru.mavrinvladislav.shifttask2025.pizza.pizza_details_screen

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
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Pizza
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Size
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Topping

class DefaultPizzaDetailsComponent @AssistedInject constructor(
    private val pizzaDetailsStoreFactory: PizzaDetailsStoreFactory,
    @Assisted("pizza")
    pizza: Pizza,
    @Assisted("componentContext")
    componentContext: ComponentContext,
    @Assisted("onBackClicked")
    private val onBackClicked: () -> Unit
) : PizzaDetailsComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { pizzaDetailsStoreFactory.create(pizza) }
    private val scope = componentScope()

    override val model: StateFlow<PizzaDetailsStore.State>
        get() = store.stateFlow

    init {
        store.labels.onEach {
            when (it) {
                is PizzaDetailsStore.Label.OnBackClicked -> {
                    onBackClicked()
                }
            }
        }.launchIn(scope)
    }

    override fun onBackClick() {
        store.accept(PizzaDetailsStore.Intent.ClickBack)
    }

    override fun updateTopping(topping: Topping) {
        store.accept(PizzaDetailsStore.Intent.UpdateTopping(topping))
    }

    override fun updateSize(size: Size) {
        store.accept(PizzaDetailsStore.Intent.UpdateSize(size))
    }

    override fun addToCart() {
    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("pizza")
            pizza: Pizza,
            @Assisted("componentContext")
            componentContext: ComponentContext,
            @Assisted("onBackClicked")
            onBackClicked: () -> Unit
        ): DefaultPizzaDetailsComponent
    }
}