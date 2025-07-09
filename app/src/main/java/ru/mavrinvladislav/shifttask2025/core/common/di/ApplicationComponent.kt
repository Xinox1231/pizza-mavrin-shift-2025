package ru.mavrinvladislav.shifttask2025.core.common.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.mavrinvladislav.shifttask2025.MainActivity
import ru.mavrinvladislav.shifttask2025.authorization.di.AuthorizationModule
import ru.mavrinvladislav.shifttask2025.pizza.di.PizzaModule
import ru.mavrinvladislav.shifttask2025.shared.di.TokenStorageModule

@ApplicationScope
@Component(
    modules = [
        NetworkModule::class,
        PresentationModule::class,
        NetworkModule::class,
        PizzaModule::class,
        AuthorizationModule::class,
        TokenStorageModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}