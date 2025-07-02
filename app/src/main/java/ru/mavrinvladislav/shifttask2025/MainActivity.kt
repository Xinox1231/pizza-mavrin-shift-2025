package ru.mavrinvladislav.shifttask2025

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.mavrinvladislav.shifttask2025.authorization.domain.use_case.CreateOtpUseCase
import ru.mavrinvladislav.shifttask2025.authorization.domain.use_case.CreateOtpUseCaseImpl
import ru.mavrinvladislav.shifttask2025.authorization.domain.use_case.SignInUseCase
import ru.mavrinvladislav.shifttask2025.ui.theme.ShiftTask2025Theme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    private val component by lazy {
        (application as PizzaApp).component
    }

    @Inject
    lateinit var usecase: CreateOtpUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val either = usecase.invoke("89990009999")
            either.fold(
                onSuccess = {
                    Log.d("SDAD", it.toString())
                },
                onFailure = {
                    Log.d("SDAD", it)
                }
            )
        }
        setContent {
            ShiftTask2025Theme {

            }
        }
    }
}
