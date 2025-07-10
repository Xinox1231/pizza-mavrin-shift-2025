package ru.mavrinvladislav.shifttask2025.profile.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.subscribeAsState
import ru.mavrinvladislav.shifttask2025.profile.presentation.edit.ProfileEditContent
import ru.mavrinvladislav.shifttask2025.profile.presentation.user.ProfileUserContent

@Composable
fun ProfileContent(component: ProfileComponent) {

    val stack by component.navStack.subscribeAsState()

    Children(
        stack = stack
    ) {
        when (val instance = it.instance) {
            is ProfileChild.ProfileEdit -> ProfileEditContent(instance.component)
            is ProfileChild.ProfileUser -> ProfileUserContent(instance.component)
        }
    }
}