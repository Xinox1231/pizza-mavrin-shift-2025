package ru.mavrinvladislav.shifttask2025.pizza

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable
import ru.mavrinvladislav.shifttask2025.pizza.pizza_catalog_screen.presentation.DefaultPizzaCatalogComponent
import ru.mavrinvladislav.shifttask2025.pizza.pizza_details_screen.DefaultPizzaDetailsComponent
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Pizza

class DefaultPizzaComponent @AssistedInject constructor(
    private val catalogComponentFactory: DefaultPizzaCatalogComponent.Factory,
    private val detailsComponentFactory: DefaultPizzaDetailsComponent.Factory,
    @Assisted("componentContext")
    componentContext: ComponentContext
) : PizzaComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<PizzaConfig>()

    override val navStack: Value<ChildStack<*, PizzaChild>> = childStack(
        source = navigation,
        serializer = PizzaConfig.serializer(),
        initialConfiguration = PizzaConfig.Catalog,
        handleBackButton = true,
        childFactory = ::child
    )

    fun child(
        config: PizzaConfig,
        componentContext: ComponentContext
    ): PizzaChild {
        return when (config) {
            PizzaConfig.Catalog -> {
                val component = catalogComponentFactory.create(
                    componentContext = componentContext,
                    clickedOnPizza = {
                        navigation.push(PizzaConfig.Details(it)) }
                )
                PizzaChild.Catalog(component)
            }

            is PizzaConfig.Details -> {
                val component = detailsComponentFactory.create(
                    pizza = config.pizza,
                    componentContext = componentContext,
                    onBackClicked = { navigation.pop() }
                )
                PizzaChild.Details(component)
            }
        }
    }

    @Serializable
    sealed interface PizzaConfig {

        @Serializable
        data object Catalog : PizzaConfig

        @Serializable
        data class Details(val pizza: Pizza) : PizzaConfig
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext")
            componentContext: ComponentContext
        ): DefaultPizzaComponent
    }
}