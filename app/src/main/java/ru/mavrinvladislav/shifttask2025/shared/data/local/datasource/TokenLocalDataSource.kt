package ru.mavrinvladislav.shifttask2025.shared.data.local.datasource

import ru.mavrinvladislav.shifttask2025.core.common.util.Either

interface TokenLocalDataSource {

    fun saveAccessToken(token: String): Either<Unit, String>
}