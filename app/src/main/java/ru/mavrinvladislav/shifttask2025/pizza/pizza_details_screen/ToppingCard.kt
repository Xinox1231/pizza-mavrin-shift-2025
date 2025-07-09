package ru.mavrinvladislav.shifttask2025.pizza.pizza_details_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import ru.mavrinvladislav.shifttask2025.R
import ru.mavrinvladislav.shifttask2025.core.design_system.component.shimmer_box.ShimmerBox
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.BrandText
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.TextStyle
import ru.mavrinvladislav.shifttask2025.core.design_system.theme.ShiftTheme
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Topping
import ru.mavrinvladislav.shifttask2025.shared.presentation.parseToString

@Composable
fun ToppingCard(topping: Topping, isSelected: Boolean = false, onClick: (Topping) -> Unit) {

    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxSize()
            .height(172.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = ShiftTheme.colors.backgroundPrimary,
        ),
        onClick = { onClick(topping) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) 4.dp else 10.dp,
            pressedElevation = if (isSelected) 4.dp else 10.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SubcomposeAsyncImage(
                    modifier = Modifier.size(88.dp),
                    model = topping.imgUrl,
                    contentDescription = topping.type.parseToString(context),
                    loading = {
                        ShimmerBox(
                            modifier = Modifier.fillMaxSize(),
                            shape = RoundedCornerShape(24.dp)
                        )
                    }
                )

                Spacer(modifier = Modifier.size(12.dp))

                BrandText(
                    text = topping.type.parseToString(context),
                    textStyle = TextStyle.REGULAR_12
                )

                Spacer(modifier = Modifier.weight(1f))

                BrandText(
                    text = String.format(stringResource(R.string.price), topping.price),
                    textStyle = TextStyle.MEDIUM_14
                )
            }
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 8.dp, end = 10.dp)
                        .border(1.dp, Color.Green, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(2.dp)
                            .size(16.dp),
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.Green
                    )
                }

            }
        }
    }
}