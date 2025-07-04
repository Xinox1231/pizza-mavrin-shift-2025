package ru.mavrinvladislav.shifttask2025.authorization.presentation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable
import ru.mavrinvladislav.shifttask2025.authorization.presentation.otp_input_screen.DefaultOtpInputComponent
import ru.mavrinvladislav.shifttask2025.authorization.presentation.phone_input_screen.DefaultPhoneInputComponent

class DefaultAuthorizationComponent @AssistedInject constructor(
    private val otpInputComponentFactory: DefaultOtpInputComponent.Factory,
    private val phoneInputComponentFactory: DefaultPhoneInputComponent.Factory,
    @Assisted("onCloseClicked")
    private val onCloseClicked: () -> Unit,
    @Assisted("componentContext")
    componentContext: ComponentContext
) : AuthorizationComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<AuthorizationConfig>()

    override val navStack: Value<ChildStack<*, AuthorizationChild>> = childStack(
        source = navigation,
        serializer = AuthorizationConfig.serializer(),
        initialConfiguration = AuthorizationConfig.PhoneInput,
        handleBackButton = true,
        childFactory = ::child
    )

    fun child(
        config: AuthorizationConfig,
        componentContext: ComponentContext
    ): AuthorizationChild {
        return when (config) {

            is AuthorizationConfig.PhoneInput -> {
                val component = phoneInputComponentFactory.create(
                    componentContext = componentContext,
                    onClose = {
                        onCloseClicked()
                    },
                    onSuccessfulCreateOtp = { phone, retry ->
                        navigation.push(
                            AuthorizationConfig.OtpInput(
                                phone,
                                retry
                            )
                        )
                    }
                )
                AuthorizationChild.PhoneInput(component)
            }

            is AuthorizationConfig.OtpInput -> {
                val component = otpInputComponentFactory.create(
                    phoneNumber = config.phoneNumber,
                    retryDelay = config.retryDelay,
                    componentContext = componentContext,
                    onClose = {
                        onCloseClicked()
                    }
                )
                AuthorizationChild.OtpInput(component)
            }
        }
    }

    @Serializable
    sealed interface AuthorizationConfig {

        @Serializable
        data object PhoneInput : AuthorizationConfig

        @Serializable
        data class OtpInput(
            val phoneNumber: String,
            val retryDelay: Int
        ) : AuthorizationConfig

    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext")
            componentContext: ComponentContext,
            @Assisted("onCloseClicked")
            onCloseClicked: () -> Unit
        ): DefaultAuthorizationComponent
    }
}
