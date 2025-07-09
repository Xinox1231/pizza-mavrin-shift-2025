package ru.mavrinvladislav.shifttask2025.core.design_system.component.button_row

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.BrandText
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.TextStyle
import ru.mavrinvladislav.shifttask2025.core.design_system.theme.ShiftTheme

@Composable
inline fun <reified T> BrandSingleChoiceButtonRow(
    options: List<T>,
    selectedItem: T,
    crossinline onClick: (T) -> Unit,
    crossinline labelProvider: (T) -> String = { it.toString() }
) {
    Surface(
        color = ShiftTheme.colors.backgroundSecondary,
        shape = RoundedCornerShape(16.dp),
    ) {
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .height(44.dp)
                .padding(2.dp)
        ) {
            options.forEach { item ->
                SegmentedButton(
                    modifier = Modifier.fillMaxHeight(),
                    colors = SegmentedButtonDefaults.colors(
                        activeContainerColor = ShiftTheme.colors.backgroundPrimary,
                        activeBorderColor = Color.Transparent,
                        inactiveContainerColor = ShiftTheme.colors.backgroundSecondary,
                        inactiveBorderColor = Color.Transparent
                    ),
                    icon = {},
                    selected = selectedItem == item,
                    onClick = { onClick(item) },
                    shape = RoundedCornerShape(14.dp),
                ) {
                    BrandText(
                        text = labelProvider(item),
                        textStyle = TextStyle.BUTTON
                    )
                }
            }
        }
    }
}