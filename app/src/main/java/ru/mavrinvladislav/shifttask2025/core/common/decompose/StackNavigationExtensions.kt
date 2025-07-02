package ru.mavrinvladislav.shifttask2025.core.common.decompose

import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront

fun <C : Any> StackNavigation<C>.clearStackAndPushConfig(config: C) {
    bringToFront(config)
}
