package ru.mavrinvladislav.shifttask2025.shared.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.mavrinvladislav.shifttask2025.shared.data.local.datasource.TokenLocalDataSource
import ru.mavrinvladislav.shifttask2025.shared.data.local.datasource.TokenLocalDataSourceImpl
import ru.mavrinvladislav.shifttask2025.shared.data.local.model.TokenStorage
import ru.mavrinvladislav.shifttask2025.shared.data.local.model.TokenStorageImpl

@Module
interface TokenStorageModule {

    @Binds
    fun bindTokenDataSource(impl: TokenLocalDataSourceImpl): TokenLocalDataSource

    @Binds
    fun bindTokenStorage(impl: TokenStorageImpl): TokenStorage

    companion object {

        @Provides
        fun provideStorage(context: Context) = TokenStorageImpl(context)
    }
}