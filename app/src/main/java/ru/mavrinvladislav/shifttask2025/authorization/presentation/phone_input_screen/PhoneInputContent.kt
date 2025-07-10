package ru.mavrinvladislav.shifttask2025.authorization.presentation.phone_input_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.mavrinvladislav.shifttask2025.R
import ru.mavrinvladislav.shifttask2025.core.design_system.component.button.BrandButton
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.BrandText
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.TextStyle
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text_field.SingleLineTextInput
import ru.mavrinvladislav.shifttask2025.core.design_system.theme.ShiftTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneInputScreen(component: PhoneInputComponent) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Авторизация"
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            component.close()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            val model by component.model.collectAsState()

            LaunchedEffect(Unit) {
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                BrandText(
                    text = stringResource(R.string.phone_input_instruction),
                    textStyle = TextStyle.REGULAR_16,
                )

                Spacer(modifier = Modifier.size(24.dp))
                SingleLineTextInput(
                    text = model.phoneNumber,
                    onTextChange = {
                        component.updatePhone(it)
                    },
                    placeholderText = stringResource(R.string.phone)
                )
                Spacer(modifier = Modifier.size(24.dp))

                BrandButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.next),
                    textColor = ShiftTheme.colors.textInvert,
                    onClick = {
                        component.next()
                    }
                )
            }
            when (model.requestState) {
                PhoneInputScreenStore.State.RequestState.Initial -> Unit
                PhoneInputScreenStore.State.RequestState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center),
                            color = ShiftTheme.colors.brandPizzaPrimary
                        )
                    }
                }
            }
        }
    }
}