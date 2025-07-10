package ru.mavrinvladislav.shifttask2025.shared.data.local.datasource

import ru.mavrinvladislav.shifttask2025.shared.data.local.model.TokenStorage
import ru.mavrinvladislav.shifttask2025.core.common.util.Either
import javax.inject.Inject

class TokenLocalDataSourceImpl @Inject constructor(
    private val localStorage: TokenStorage
) : TokenLocalDataSource {
    override fun saveAccessToken(token: String) {
        localStorage.saveAccessToken(token)
    }

    override fun getAccessToken(): String? = localStorage.getAccessToken()

}