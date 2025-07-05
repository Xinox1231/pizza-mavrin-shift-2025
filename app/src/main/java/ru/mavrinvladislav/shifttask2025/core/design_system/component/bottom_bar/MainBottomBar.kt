package ru.mavrinvladislav.shifttask2025.core.design_system.component.bottom_bar

import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.mavrinvladislav.shifttask2025.core.design_system.theme.ShiftTheme
import ru.mavrinvladislav.shifttask2025.main_component.MainTab

@Composable
fun MainBottomBar(
    tabs: List<MainTab>,
    selectedTab: MainTab,
    modifier: Modifier = Modifier,
    onClick: (MainTab) -> Unit
) {
    BottomAppBar(
        modifier = modifier,
        containerColor = ShiftTheme.colors.backgroundPrimary
    ) {
        tabs.forEach { tab ->
            MainNavigationBar(
                selected = selectedTab == tab,
                iconResId = tab.iconResId,
                text = stringResource(tab.textResId),
                onClick = { onClick(tab) }
            )
        }
    }
}