package ru.mavrinvladislav.shifttask2025.shared.data.local.model

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import javax.inject.Inject

class TokenStorageImpl @Inject constructor(
    context: Context
) : TokenStorage {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(AUTH_STORAGE, Context.MODE_PRIVATE)

    private companion object {
        const val LOG_TAG = "AuthStorage"
        const val AUTH_STORAGE = "auth_storage"
        const val ACCESS_TOKEN_KEY = "auth_token"
    }

    override fun saveAccessToken(accessToken: String) {
        sharedPreferences.edit {
            putString(ACCESS_TOKEN_KEY, accessToken)
        }
    }

    override fun getAccessToken(): String? {
        return sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
    }

    override fun deleteAccessToken() {
        sharedPreferences.edit {
            remove(ACCESS_TOKEN_KEY)
        }
    }
}