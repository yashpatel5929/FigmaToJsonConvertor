package com.example.figmatojsongenerator.di

import com.example.figmatojsongenerator.data.local.PrefrenceManager
import com.example.figmatojsongenerator.data.repositories.AuthRepositoriesImpl
import com.example.figmatojsongenerator.domain.repositories.AuthRepositories
import com.example.figmatojsongenerator.domain.usecase.AuthRepositoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        preferencesManager: PrefrenceManager
    ): AuthRepositories {
        return AuthRepositoriesImpl(
            preferencesManager
        )
    }

    @Provides
    @Singleton
    fun provideAuthUseCase(authRepository: AuthRepositories): AuthRepositoriesUseCase {
        return AuthRepositoriesUseCase(authRepository)
    }
}