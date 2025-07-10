package ru.mavrinvladislav.shifttask2025.profile.presentation.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.mavrinvladislav.shifttask2025.R
import ru.mavrinvladislav.shifttask2025.core.design_system.component.app_bar.MainAppBar
import ru.mavrinvladislav.shifttask2025.core.design_system.component.button.BrandButton
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.BrandText
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.TextStyle
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text_field.SingleLineTextInputWithLabel

@Composable
fun ProfileUserContent(component: ProfileUserComponent) {

    MainAppBar(text = stringResource(R.string.profile))

    val model by component.model.collectAsState()

    when (val state = model.userState) {
        is ProfileUserStore.State.UserState.Initial -> Unit
        is ProfileUserStore.State.UserState.Loading -> {

        }

        is ProfileUserStore.State.UserState.Loaded -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, top = 24.dp, end = 16.dp)
            ) {
                with(state.user) {
                    SingleLineTextInputWithLabel(
                        textLabel = "Имя*",
                        text = firstName,
                        readOnly = true
                    ) { }

                    Spacer(modifier = Modifier.size(16.dp))

                    SingleLineTextInputWithLabel(
                        textLabel = "Фамилия*",
                        text = lastName,
                        readOnly = true
                    ) { }

                    Spacer(modifier = Modifier.size(16.dp))

                    SingleLineTextInputWithLabel(
                        textLabel = "Отчество*",
                        text = middleName,
                        readOnly = true
                    ) { }

                    Spacer(modifier = Modifier.size(16.dp))

                    SingleLineTextInputWithLabel(
                        textLabel = "Номер телефона",
                        text = phone,
                        readOnly = true
                    ) { }

                    Spacer(modifier = Modifier.size(16.dp))

                    SingleLineTextInputWithLabel(
                        textLabel = "Город",
                        text = city,
                        readOnly = true
                    ) { }

                    Spacer(modifier = Modifier.size(32.dp))

                    BrandButton(
                        text = "Обновить данные"
                    ) { component.clickOnUpdateData() }

                    Spacer(modifier = Modifier.size(8.dp))

                    BrandButton(
                        text = "Выйти"
                    ) { component.clickOnLogOut() }
                }
            }
        }

        is ProfileUserStore.State.UserState.Error -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BrandText(text = state.reason, textStyle = TextStyle.MEDIUM_16)

                Spacer(modifier = Modifier.size(16.dp))

                BrandButton(
                    text = stringResource(R.string.retry)
                ) { component.retry() }
            }
        }
    }
}
