package ru.mavrinvladislav.shifttask2025.core.design_system.component.button

import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.BrandText
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.TextStyle
import ru.mavrinvladislav.shifttask2025.core.design_system.theme.ShiftTheme

@Composable
fun BrandTextButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textColor: Color = ShiftTheme.colors.textInvert,
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .sizeIn(
                minHeight = 56.dp
            ),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            contentColor = ShiftTheme.colors.textInvert,
            disabledContentColor = ShiftTheme.colors.brandPizzaDisabled,
        )
    ) {
        BrandText(
            text = text,
            textStyle = TextStyle.BUTTON,
            color = textColor
        )
    }
}