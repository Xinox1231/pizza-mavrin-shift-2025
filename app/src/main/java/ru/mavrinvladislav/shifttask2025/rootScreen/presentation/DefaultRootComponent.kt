package ru.mavrinvladislav.shifttask2025.rootScreen.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import ru.mavrinvladislav.shifttask2025.authorization.domain.model.AuthState
import ru.mavrinvladislav.shifttask2025.authorization.domain.use_case.IsAuthorizedUseCase
import ru.mavrinvladislav.shifttask2025.authorization.presentation.DefaultAuthorizationComponent
import ru.mavrinvladislav.shifttask2025.core.common.EventBusController
import ru.mavrinvladislav.shifttask2025.core.common.decompose.componentScope
import ru.mavrinvladislav.shifttask2025.core.presentation.AppEvent
import ru.mavrinvladislav.shifttask2025.main_screen.DefaultMainComponent
import ru.mavrinvladislav.shifttask2025.splash.presentation.DefaultSplashComponent

class DefaultRootComponent @AssistedInject constructor(
    private val eventBusController: EventBusController,
    private val authorizationComponentFactory: DefaultAuthorizationComponent.Factory,
    private val mainComponentFactory: DefaultMainComponent.Factory,
    private val splashComponentFactory: DefaultSplashComponent.Factory,
    private val isAuthorizedUseCase: IsAuthorizedUseCase,
    @Assisted("componentContext")
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<RootConfig>()

    private val scope = componentScope()

    private val startScreen = RootConfig.Splash

    override val childStack: Value<ChildStack<*, RootChild>> = childStack(
        source = navigation,
        serializer = RootConfig.serializer(),
        initialConfiguration = startScreen,
        childFactory = ::child
    )

    init {
        scope.launch {
            //Проверка на входе, есть ли токен
            isAuthorizedUseCase()
                .collectLatest { authState ->
                    when (authState) {
                        AuthState.Authorized -> onAuthorized()
                        AuthState.UnAuthorized -> onUnAuthorized()
                    }
                }
        }

        scope.launch {
            eventBusController.eventBus
                .collectLatest {
                    when (it) {
                        AppEvent.LOGOUT -> {
                            onUnAuthorized()
                        }

                        AppEvent.AUTHORIZE -> {
                            onAuthorized()
                        }
                    }
                }
        }
    }

    private fun onAuthorized() {
        //Удаляем Authorization и восстанавливаем стек
        navigation.bringToFront(RootConfig.Main)
    }

    private fun onUnAuthorized() {
        navigation.push(RootConfig.Authorization)
    }

    fun child(
        config: RootConfig,
        componentContext: ComponentContext
    ): RootChild {
        return when (config) {
            is RootConfig.Authorization -> {
                val component = authorizationComponentFactory.create(
                    componentContext = componentContext
                )
                RootChild.Authorization(component)
            }

            is RootConfig.Main -> {
                val component = mainComponentFactory.create(
                    componentContext = componentContext
                )
                RootChild.Main(component)
            }

            is RootConfig.Splash -> {
                val component = splashComponentFactory.create(
                    onAuthorized = {
                        onAuthorized()
                    },
                    onUnAuthorized = {
                        onUnAuthorized()
                    },
                    componentContext = componentContext
                )
                RootChild.Splash(component)
            }
        }
    }

    @Serializable
    sealed interface RootConfig {

        @Serializable
        data object Splash : RootConfig

        @Serializable
        data object Authorization : RootConfig

        @Serializable
        data object Main : RootConfig

    }

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("componentContext")
            componentContext: ComponentContext
        ): DefaultRootComponent
    }

    companion object {
        private const val LOG_TAG = "DefaultRootComponent"
    }
}