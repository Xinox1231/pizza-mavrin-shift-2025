package ru.mavrinvladislav.shifttask2025.pizza.pizza_catalog_screen.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import ru.mavrinvladislav.shifttask2025.R
import ru.mavrinvladislav.shifttask2025.core.design_system.component.shimmer_box.ShimmerBox
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.BrandText
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.TextStyle
import ru.mavrinvladislav.shifttask2025.core.design_system.theme.ShiftTheme
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Pizza

@Composable
fun PizzaCatalogCard(
    pizza: Pizza,
    onClick: (Pizza) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        color = ShiftTheme.colors.backgroundPrimary,
        onClick = { onClick(pizza) }
    ) {
        Row {

            Spacer(modifier = Modifier.size(16.dp))

            SubcomposeAsyncImage(
                model = pizza.imgUrl,
                contentDescription = pizza.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(116.dp),
                loading = {
                    ShimmerBox(
                        Modifier
                            .fillMaxSize(),
                        shape = CircleShape
                    )
                },
            )

            Spacer(modifier = Modifier.size(24.dp))

            Column(
                modifier = Modifier.weight(1f),
            ) {
                BrandText(
                    text = pizza.name,
                    textStyle = TextStyle.MEDIUM_16
                )

                Spacer(modifier = Modifier.size(8.dp))

                BrandText(
                    text = pizza.description,
                    textStyle = TextStyle.REGULAR_12
                )

                Spacer(modifier = Modifier.weight(1f))

                BrandText(
                    text = String.format(
                        stringResource(R.string.pizza_start_price),
                        pizza.getStartPrice()
                    ),
                    textStyle = TextStyle.MEDIUM_16
                )
            }

            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
@Preview
fun PizzaCatalogCardSkeleton() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        color = ShiftTheme.colors.backgroundPrimary
    ) {
        Row {

            Spacer(modifier = Modifier.size(16.dp)) // тот же отступ, как у оригинала

            ShimmerBox(
                modifier = Modifier
                    .size(116.dp),
                shape = CircleShape
            )

            Spacer(modifier = Modifier.size(24.dp)) // тот же отступ

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                ShimmerBox(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(24.dp),
                    shape = RoundedCornerShape(4.dp)
                )

                Spacer(modifier = Modifier.size(8.dp))

                ShimmerBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp),
                    shape = RoundedCornerShape(4.dp)
                )

                Spacer(modifier = Modifier.size(8.dp))

                ShimmerBox(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .height(16.dp),
                    shape = RoundedCornerShape(4.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                ShimmerBox(
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .height(20.dp),
                    shape = RoundedCornerShape(4.dp)
                )
            }

            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}