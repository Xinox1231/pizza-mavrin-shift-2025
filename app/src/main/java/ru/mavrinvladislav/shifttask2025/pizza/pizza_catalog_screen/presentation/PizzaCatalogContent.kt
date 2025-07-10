package ru.mavrinvladislav.shifttask2025.pizza.pizza_catalog_screen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.mavrinvladislav.shifttask2025.R
import ru.mavrinvladislav.shifttask2025.core.design_system.component.app_bar.MainAppBar
import ru.mavrinvladislav.shifttask2025.core.design_system.component.button.BrandButton
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.BrandText
import ru.mavrinvladislav.shifttask2025.core.design_system.component.text.TextStyle
import ru.mavrinvladislav.shifttask2025.shared.domain.model.Pizza

@Composable
fun PizzaCatalogContent(component: PizzaCatalogComponent) {

    Column(modifier = Modifier.fillMaxSize()) {
        MainAppBar(text = stringResource(R.string.pizza))

        val model by component.model.collectAsState()

        when (val state = model.catalogState) {
            is PizzaListStore.State.CatalogState.Initial -> Unit

            is PizzaListStore.State.CatalogState.Error -> {
                ErrorContent(state.reason) { component.retryRequest() }
            }

            is PizzaListStore.State.CatalogState.Loaded -> {
                MainContent(catalog = state.list) { component.clickOnPizza(it) }
            }

            is PizzaListStore.State.CatalogState.Loading -> {
                LoadingContent()
            }
        }
    }
}

@Composable
private fun ErrorContent(reason: String, onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            BrandText(
                text = reason,
                textStyle = TextStyle.MEDIUM_16
            )

            Spacer(modifier = Modifier.size(16.dp))

            BrandButton(
                text = stringResource(R.string.retry)
            ) { onClick() }
        }
    }
}

@Composable
private fun LoadingContent() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(10) {
            PizzaCatalogCardSkeleton()
        }
    }
}

@Composable
private fun MainContent(
    catalog: List<Pizza>,
    onPizzaClick: (Pizza) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(catalog) { pizza ->
            PizzaCatalogCard(
                pizza = pizza,
                onClick = onPizzaClick
            )
        }
    }
}