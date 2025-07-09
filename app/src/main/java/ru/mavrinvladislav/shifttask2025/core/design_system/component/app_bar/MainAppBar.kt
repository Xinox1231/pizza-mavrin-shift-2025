package ru.mavrinvladislav.shifttask2025.core.design_system.component.app_bar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.BrandText
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.TextStyle
import ru.mavrinvladislav.shifttask2025.core.design_system.theme.ShiftTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(
    text: String? = null,
    navigationIcon: ActionIcon? = null,
    actions: List<ActionIcon>? = null
) {
    TopAppBar(
        title = {
            text?.let {
                BrandText(
                    text = it,
                    textStyle = TextStyle.TITLE_H2
                )
            }
        },
        navigationIcon = {
            navigationIcon?.let {
                IconButton(
                    onClick = it.onClick
                ) {
                    Icon(
                        painter = painterResource(navigationIcon.iconResId),
                        contentDescription = navigationIcon.contentDescription
                    )
                }
            }
        },
        actions = {
            actions?.let {
                it.forEach {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(it.iconResId),
                            contentDescription = it.contentDescription
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ShiftTheme.colors.backgroundPrimary
        )
    )
}