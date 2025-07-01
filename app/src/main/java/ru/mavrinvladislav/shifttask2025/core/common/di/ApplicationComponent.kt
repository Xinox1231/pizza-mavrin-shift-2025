package ru.mavrinvladislav.shifttask2025.core.common.di

import dagger.Component
import ru.mavrinvladislav.shifttask2025.MainActivity

@ApplicationScope
@Component(
    modules = [
        NetworkModule::class,
        PresentationModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(
        ): ApplicationComponent
    }
}