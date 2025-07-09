@file:OptIn(ExperimentalCoroutinesApi::class)

package ru.mavrinvladislav.shifttask2025.pizza.pizza_catalog_screen.presentation

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
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Pizza

class DefaultPizzaCatalogComponent @AssistedInject constructor(
    private val pizzaCatalogStoreFactory: PizzaListStoreFactory,
    @Assisted("componentContext")
    componentContext: ComponentContext,
    @Assisted("clickedOnPizza")
    clickedOnPizza: (Pizza) -> Unit
) : PizzaCatalogComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { pizzaCatalogStoreFactory.create() }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect { label ->
                when (label) {
                    is PizzaListStore.Label.ClickedOnPizza -> {
                        clickedOnPizza(label.pizza)
                    }
                }
            }
        }
    }

    override val model: StateFlow<PizzaListStore.State>
        get() = store.stateFlow

    override fun retryRequest() {
        store.accept(PizzaListStore.Intent.Reload)
    }

    override fun clickOnPizza(pizza: Pizza) {
        store.accept(PizzaListStore.Intent.ClickOnPizza(pizza))
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext")
            componentContext: ComponentContext,
            @Assisted("clickedOnPizza")
            clickedOnPizza: (Pizza) -> Unit
        ): DefaultPizzaCatalogComponent
    }
}