package ru.mavrinvladislav.shifttask2025.core.design_system.component.button

import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.BrandText
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.TextStyle
import ru.mavrinvladislav.shifttask2025.core.design_system.theme.ShiftTheme

@Composable
fun BrandButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textColor: Color = ShiftTheme.colors.textInvert,
    shape: Shape = RoundedCornerShape(16.dp),
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .sizeIn(
                minHeight = 56.dp
            ),
        enabled = enabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = ShiftTheme.colors.brandPizzaPrimary,
            contentColor = ShiftTheme.colors.textInvert,
            disabledContentColor = ShiftTheme.colors.brandPizzaDisabled,
            disabledContainerColor = ShiftTheme.colors.textInvert
        )
    ) {
        BrandText(
            text = text,
            textStyle = TextStyle.BUTTON,
            color = textColor
        )
    }
}

@Preview
@Composable
private fun BrandButtonPreview() {
    BrandButton(text = "Продолжить") {

    }
}