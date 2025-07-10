package ru.mavrinvladislav.shifttask2025.pizza.pizza_details_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import ru.mavrinvladislav.shifttask2025.R
import ru.mavrinvladislav.shifttask2025.core.design_system.component.app_bar.ActionIcon
import ru.mavrinvladislav.shifttask2025.core.design_system.component.app_bar.MainAppBar
import ru.mavrinvladislav.shifttask2025.core.design_system.component.button.BrandButton
import ru.mavrinvladislav.shifttask2025.core.design_system.component.button_row.BrandSingleChoiceButtonRow
import ru.mavrinvladislav.shifttask2025.core.design_system.component.shimmer_box.ShimmerBox
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.BrandText
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.TextStyle
import ru.mavrinvladislav.shifttask2025.core.design_system.theme.ShiftTheme
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Pizza
import ru.mavrinvladislav.shifttask2025.shared.presentation.parseToString
import ru.mavrinvladislav.shifttask2025.shared.presentation.toLocalizedString
import kotlin.math.ceil

@Composable
fun PizzaDetailsContent(component: PizzaDetailsComponent) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = ShiftTheme.colors.backgroundPrimary
    ) {

        val model by component.model.collectAsState()
        val pizza: Pizza = model.pizza
        val context = LocalContext.current

        Column {
            MainAppBar(
                text = stringResource(R.string.pizza),
                navigationIcon = ActionIcon(
                    iconResId = R.drawable.ic_arrow_back,
                    onClick = { component.onBackClick() }
                )
            )

            LazyColumn {

                item { Spacer(modifier = Modifier.size(24.dp)) }

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        SubcomposeAsyncImage(
                            model = pizza.imgUrl,
                            modifier = Modifier.size(220.dp),
                            contentDescription = null,
                            loading = {
                                ShimmerBox(
                                    modifier = Modifier.size(220.dp),
                                    shape = CircleShape
                                )
                            }
                        )
                    }
                }

                item { Spacer(modifier = Modifier.size(32.dp)) }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                    ) {

                        BrandText(
                            text = pizza.name,
                            textStyle = TextStyle.TITLE_H2,
                        )

                        Spacer(modifier = Modifier.size(8.dp))

                        BrandText(
                            text = pizza.description,
                            textStyle = TextStyle.REGULAR_14,
                        )

                        Spacer(modifier = Modifier.size(8.dp))

                        BrandText(
                            text = pizza.ingredients.toLocalizedString(context),
                            textStyle = TextStyle.REGULAR_16
                        )

                        Spacer(modifier = Modifier.size(24.dp))

                        BrandSingleChoiceButtonRow(
                            options = pizza.sizes,
                            selectedItem = model.selectedSize,
                            onClick = { component.updateSize(it) },
                            labelProvider = { it.type.parseToString(context) }
                        )

                        Spacer(modifier = Modifier.size(24.dp))

                        BrandText(
                            text = stringResource(R.string.pizza_add_toppings),
                            textStyle = TextStyle.MEDIUM_16
                        )
                    }

                    val countInRow = 3
                    val bottomPadding = 32.dp
                    val gridHeight by remember(pizza.toppings.size) {
                        derivedStateOf {
                            val rows = ceil(pizza.toppings.size / countInRow.toDouble()).toInt()
                            (rows * 172).dp + (rows * 8).dp + bottomPadding
                        }
                    }

                    LazyVerticalGrid(
                        modifier = Modifier
                            .height(gridHeight),
                        columns = GridCells.Fixed(countInRow),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        userScrollEnabled = false,
                        contentPadding = PaddingValues(
                            start = 16.dp,
                            top = 16.dp,
                            end = 16.dp,
                        )
                    ) {
                        items(pizza.toppings) { topping ->
                            ToppingCard(
                                topping = topping,
                                isSelected = model.selectedToppings.contains(topping)
                            ) { component.updateTopping(it) }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp)
                    ) {

                        BrandButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(R.string.pizza_add_to_cart),
                        ) { component.addToCart() }
                    }
                }
            }
        }
    }
}