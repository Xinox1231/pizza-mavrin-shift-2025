package ru.mavrinvladislav.shifttask2025.profile.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.mavrinvladislav.shifttask2025.profile.data.remote.ProfileService
import ru.mavrinvladislav.shifttask2025.profile.data.remote.datasource.ProfileRemoteDataSource
import ru.mavrinvladislav.shifttask2025.profile.data.remote.datasource.ProfileRemoteDataSourceImpl
import ru.mavrinvladislav.shifttask2025.profile.data.repository.ProfileRepositoryImpl
import ru.mavrinvladislav.shifttask2025.profile.domain.repository.ProfileRepository
import ru.mavrinvladislav.shifttask2025.profile.domain.usecase.GetSessionUseCase
import ru.mavrinvladislav.shifttask2025.profile.domain.usecase.GetSessionUseCaseImpl

@Module
interface ProfileModule {

    @Binds
    fun bindProfileRepository(impl: ProfileRepositoryImpl): ProfileRepository

    @Binds
    fun bindProfileRemoteDataSource(impl: ProfileRemoteDataSourceImpl): ProfileRemoteDataSource

    @Binds
    fun bindGetSessionUseCase(impl: GetSessionUseCaseImpl): GetSessionUseCase

    companion object {

        @Provides
        fun provideProfileService(retrofit: Retrofit) = retrofit.create(ProfileService::class.java)
    }
}