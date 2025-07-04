package ru.mavrinvladislav.shifttask2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.arkivanov.decompose.defaultComponentContext
import ru.mavrinvladislav.shifttask2025.core.design_system.theme.MainTheme
import ru.mavrinvladislav.shifttask2025.rootScreen.presentation.DefaultRootComponent
import ru.mavrinvladislav.shifttask2025.rootScreen.presentation.RootContent
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    private val component by lazy {
        (application as PizzaApp).component
    }

    @Inject
    lateinit var rootComponentFactory: DefaultRootComponent.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        val componentContext = defaultComponentContext()
        setContent {
            MainTheme {
                RootContent(rootComponentFactory.create(componentContext))
            }
        }
    }
}
