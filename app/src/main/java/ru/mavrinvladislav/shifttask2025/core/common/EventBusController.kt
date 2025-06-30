package ru.mavrinvladislav.shifttask2025.core.common

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ru.mavrinvladislav.shifttask2025.core.common.di.ApplicationScope
import ru.mavrinvladislav.shifttask2025.core.presentation.AppEvent
import javax.inject.Inject

@ApplicationScope
class
EventBusController @Inject constructor() {

    private val _eventBus = MutableSharedFlow<AppEvent>()
    val eventBus = _eventBus.asSharedFlow()

    suspend fun publishEvent(appEvent: AppEvent) {
        _eventBus.emit(appEvent)
    }
}