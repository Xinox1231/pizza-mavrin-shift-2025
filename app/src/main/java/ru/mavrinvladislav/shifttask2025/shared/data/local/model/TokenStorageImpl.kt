package ru.mavrinvladislav.shifttask2025.shared.data.local.model

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import javax.inject.Inject

class TokenStorageImpl @Inject constructor(
    context: Context
) : TokenStorage {

    // Инициализация SharedPreferences для хранения токенов
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(AUTH_STORAGE, Context.MODE_PRIVATE)

    // Статические константы для логирования и ключей в SharedPreferences
    private companion object {
        const val LOG_TAG = "AuthStorage" // Тег для логов
        const val AUTH_STORAGE = "auth_storage" // Название файла SharedPreferences
        const val ACCESS_TOKEN_KEY = "auth_token" // Ключ для хранения access токена
    }

    /**
     * Сохраняет access токен в SharedPreferences.
     */
    override fun saveAccessToken(accessToken: String) {
        sharedPreferences.edit {
            putString(ACCESS_TOKEN_KEY, accessToken) // Сохраняем access токен
        }
    }

    /**
     * Возвращает сохраненный access токен из SharedPreferences.
     */
    override fun getAccessToken(): String? {
        Log.d(LOG_TAG, "Получение access токена")
        return sharedPreferences.getString(ACCESS_TOKEN_KEY, null) // Читаем access токен
    }

    override fun deleteAccessToken() {
        Log.d(LOG_TAG, "Очистка токенов")
        sharedPreferences.edit {
            remove(ACCESS_TOKEN_KEY) // Удаляем access токен
        }
    }
}