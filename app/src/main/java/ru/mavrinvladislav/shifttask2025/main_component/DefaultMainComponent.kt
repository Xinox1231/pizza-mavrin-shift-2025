package ru.mavrinvladislav.shifttask2025.main_component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable
import ru.mavrinvladislav.shifttask2025.orders.DefaultOrdersComponent
import ru.mavrinvladislav.shifttask2025.pizza.DefaultPizzaComponent
import ru.mavrinvladislav.shifttask2025.profile.DefaultProfileComponent
import ru.mavrinvladislav.shifttask2025.trash_can.DefaultTrashCanComponent

class DefaultMainComponent @AssistedInject constructor(
    private val pizzaComponentFactory: DefaultPizzaComponent.Factory,
    private val ordersComponentFactory: DefaultOrdersComponent.Factory,
    private val trashCanComponentFactory: DefaultTrashCanComponent.Factory,
    private val profileComponentFactory: DefaultProfileComponent.Factory,
    @Assisted("componentContext")
    componentContext: ComponentContext
) : MainComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<MainConfig>()

    override val navigationStack = childStack(
        source = navigation,
        serializer = MainConfig.serializer(),
        initialConfiguration = MainConfig.Pizza,
        childFactory = ::child
    )


    override fun onTabSelected(tab: MainTab) {
        val configuration = tab.toConfiguration()
        navigation.bringToFront(configuration)
    }

    fun child(config: MainConfig, componentContext: ComponentContext): MainChild {
        return when (config) {
            MainConfig.Pizza -> {
                val component = pizzaComponentFactory.create(
                    componentContext = componentContext
                )
                MainChild.Pizza(component)
            }

            MainConfig.Orders -> {
                val component = ordersComponentFactory.create(
                    componentContext = componentContext
                )
                MainChild.Orders(component)
            }


            MainConfig.TrashCan -> {
                val component = trashCanComponentFactory.create(
                    componentContext = componentContext
                )
                MainChild.TrashCan(component)
            }

            MainConfig.Profile -> {
                val component = profileComponentFactory.create(
                    componentContext = componentContext
                )
                MainChild.Profile(component)
            }

        }
    }

    @Serializable
    sealed interface MainConfig {

        @Serializable
        data object Pizza : MainConfig

        @Serializable
        data object Orders : MainConfig

        @Serializable
        data object TrashCan : MainConfig

        @Serializable
        data object Profile : MainConfig
    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("componentContext")
            componentContext: ComponentContext
        ): DefaultMainComponent
    }

}