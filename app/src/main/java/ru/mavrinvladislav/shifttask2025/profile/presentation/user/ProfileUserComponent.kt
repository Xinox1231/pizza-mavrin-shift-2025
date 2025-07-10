package ru.mavrinvladislav.shifttask2025.profile.presentation.user

import kotlinx.coroutines.flow.StateFlow

interface ProfileUserComponent {

    val model: StateFlow<ProfileUserStore.State>

    fun clickOnUpdateData()

    fun clickOnLogOut()

    fun retry()
}