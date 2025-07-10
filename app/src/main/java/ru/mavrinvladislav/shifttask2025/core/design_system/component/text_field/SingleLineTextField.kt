package ru.mavrinvladislav.shifttask2025.core.design_system.component.text_field

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.BrandText
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.TextStyle
import ru.mavrinvladislav.shifttask2025.core.design_system.theme.ShiftTheme

@Composable
fun SingleLineTextInput(
    modifier: Modifier = Modifier,
    text: String,
    placeholderText: String? = null,
    shape: RoundedCornerShape = RoundedCornerShape(16.dp),
    singleLine: Boolean = true,
    readOnly: Boolean = false,
    onTextChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = text,
        singleLine = singleLine,
        shape = shape,
        readOnly = readOnly,
        placeholder = placeholderText?.let {
            {
                BrandText(
                    text = placeholderText,
                    color = ShiftTheme.colors.borderMedium,
                    textStyle = TextStyle.REGULAR_16
                )
            }
        },
        onValueChange = {
            onTextChange(it)
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = ShiftTheme.colors.backgroundPrimary,
            unfocusedContainerColor = ShiftTheme.colors.backgroundPrimary,
            focusedPlaceholderColor = ShiftTheme.colors.borderMedium,
            unfocusedPlaceholderColor = ShiftTheme.colors.borderMedium,
            focusedTextColor = ShiftTheme.colors.textPrimary,
            unfocusedTextColor = ShiftTheme.colors.textTertiary,
            errorBorderColor = ShiftTheme.colors.borderMedium,
            errorContainerColor = ShiftTheme.colors.borderMedium,
            focusedBorderColor = ShiftTheme.colors.borderMedium,
            unfocusedBorderColor = ShiftTheme.colors.borderMedium,
            errorPlaceholderColor = ShiftTheme.colors.borderMedium,
        ),
    )
}