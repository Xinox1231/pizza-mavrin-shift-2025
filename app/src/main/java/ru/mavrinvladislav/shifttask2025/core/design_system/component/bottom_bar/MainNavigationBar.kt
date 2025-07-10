package ru.mavrinvladislav.shifttask2025.core.design_system.component.bottom_bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.BrandText
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.TextStyle
import ru.mavrinvladislav.shifttask2025.core.design_system.theme.ShiftTheme

@Composable
fun RowScope.MainNavigationBar(
    selected: Boolean,
    iconResId: Int,
    contentDescription: String? = null,
    text: String? = null,
    onClick: () -> Unit,
) {


    NavigationBarItem(
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = ShiftTheme.colors.brandPizzaPrimary,
            selectedTextColor = ShiftTheme.colors.brandPizzaPrimary,
            unselectedIconColor = ShiftTheme.colors.indicatorMedium,
            unselectedTextColor = ShiftTheme.colors.textQuaternary
        ),
        icon = {
            Icon(
                painter = painterResource(iconResId),
                contentDescription = contentDescription
            )
        },
        label = {
            text?.let {
                BrandText(
                    text = it,
                    textStyle = TextStyle.BOTTOM_BAR,
                    color = if (selected) {
                        ShiftTheme.colors.brandPizzaPrimary
                    } else {
                        ShiftTheme.colors.textQuaternary
                    }
                )
            }
        },
        selected = selected,
        onClick = onClick,
    )
}