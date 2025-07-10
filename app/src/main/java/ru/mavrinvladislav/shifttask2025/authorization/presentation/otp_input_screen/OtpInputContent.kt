package ru.mavrinvladislav.shifttask2025.authorization.presentation.otp_input_screen

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.mavrinvladislav.shifttask2025.R
import ru.mavrinvladislav.shifttask2025.core.design_system.component.button.BrandButton
import ru.mavrinvladislav.shifttask2025.core.design_system.component.button.BrandTextButton
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.BrandText
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.TextStyle
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text_field.SingleLineTextInput
import ru.mavrinvladislav.shifttask2025.core.design_system.theme.ShiftTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpInputScreen(component: OtpInputComponent) {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.authorization)
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
            val context = LocalContext.current

            val model by component.model.collectAsState()

            val keyboardController = LocalSoftwareKeyboardController.current

            LaunchedEffect(Unit) {
                component.event.collect {
                    with(context) {
                        when (it) {
                            is OtpInputEvent.ErrorRequestOtp -> {
                                Toast.makeText(
                                    this,
                                    String.format(
                                        getString(R.string.otp_request_er),
                                        it.reason
                                    ),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            is OtpInputEvent.ErrorSignIn -> {
                                Toast.makeText(
                                    this,
                                    String.format(
                                        getString(R.string.login_request_er),
                                        it.reason
                                    ),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            is OtpInputEvent.SuccessfulRequestOtp -> {
                                Toast.makeText(
                                    this,
                                    getString(R.string.otp_request_success),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            is OtpInputEvent.SuccessfulSignIn -> {
                                Toast.makeText(
                                    this,
                                    getString(R.string.login_request_success),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            is OtpInputEvent.HideKeyBoard -> {
                                keyboardController?.hide()
                            }
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
            ) {

                BrandText(
                    text = stringResource(R.string.successful_otp_request, model.phoneNumber),
                    textStyle = TextStyle.REGULAR_16,
                )

                Spacer(modifier = Modifier.size(24.dp))
                SingleLineTextInput(
                    text = model.otpCode,
                    onTextChange = {
                        component.updateOtp(it)
                    },
                    placeholderText = stringResource(R.string.otp_input_instruction)
                )
                Spacer(modifier = Modifier.size(24.dp))

                BrandButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.next),
                    textColor = ShiftTheme.colors.textInvert,
                    onClick = {
                        component.signIn()
                    }
                )

                Spacer(modifier = Modifier.size(16.dp))

                when (val state = model.coolDownState) {
                    is OtpInputStore.State.CoolDownState.Allowed -> {
                        BrandTextButton(
                            onClick = {
                                component.requestOtp()
                            },
                            text = "Запросить код ещё раз",
                            textColor = ShiftTheme.colors.textPrimary
                        )
                    }

                    is OtpInputStore.State.CoolDownState.Waiting -> {
                        BrandText(
                            text = String.format(
                                stringResource(R.string.otp_request_cooldown),
                                state.retryDelay
                            ),
                            textStyle = TextStyle.REGULAR_16
                        )
                    }
                }
            }
            when (model.loadingState) {
                is OtpInputStore.State.LoadingState.Initial -> Unit

                is OtpInputStore.State.LoadingState.Loading -> {
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
