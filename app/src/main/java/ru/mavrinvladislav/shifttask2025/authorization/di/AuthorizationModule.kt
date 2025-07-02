package ru.mavrinvladislav.shifttask2025.authorization.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.mavrinvladislav.shifttask2025.authorization.data.remote.AuthorizationService
import ru.mavrinvladislav.shifttask2025.authorization.data.remote.source.AuthorizationRemoteDataSource
import ru.mavrinvladislav.shifttask2025.authorization.data.remote.source.AuthorizationRemoteDataSourceImpl
import ru.mavrinvladislav.shifttask2025.authorization.data.repository.AuthorizationRepositoryImpl
import ru.mavrinvladislav.shifttask2025.authorization.domain.repository.AuthorizationRepository
import ru.mavrinvladislav.shifttask2025.authorization.domain.use_case.CreateOtpUseCase
import ru.mavrinvladislav.shifttask2025.authorization.domain.use_case.CreateOtpUseCaseImpl
import ru.mavrinvladislav.shifttask2025.authorization.domain.use_case.SignInUseCase
import ru.mavrinvladislav.shifttask2025.authorization.domain.use_case.SignInUseCaseImpl

@Module
interface AuthorizationModule {

    @Binds
    fun bindAuthorizationRepository(impl: AuthorizationRepositoryImpl): AuthorizationRepository

    @Binds
    fun bindRemoteDataSource(impl: AuthorizationRemoteDataSourceImpl): AuthorizationRemoteDataSource

    @Binds
    fun bindSignInUseCase(impl: SignInUseCaseImpl): SignInUseCase

    @Binds
    fun bindCreateOtpUseCase(impl: CreateOtpUseCaseImpl): CreateOtpUseCase

    companion object {

        @Provides
        fun provideApiService(retrofit: Retrofit) =
            retrofit.create(AuthorizationService::class.java)
    }
}