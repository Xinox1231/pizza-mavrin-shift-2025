package ru.mavrinvladislav.shifttask2025.profile.presentation

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import ru.mavrinvladislav.shifttask2025.profile.presentation.edit.ProfileEditComponent
import ru.mavrinvladislav.shifttask2025.profile.presentation.user.ProfileUserComponent

interface ProfileComponent {

    val navStack: Value<ChildStack<*, ProfileChild>>

}

sealed interface ProfileChild {

    data class ProfileUser(val component: ProfileUserComponent) : ProfileChild

    data class ProfileEdit(val component: ProfileEditComponent) : ProfileChild

}