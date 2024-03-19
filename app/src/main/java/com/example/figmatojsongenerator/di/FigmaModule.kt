package com.example.figmatojsongenerator.di

import com.example.figmatojsongenerator.data.remote.FigmaRestApi
import com.example.figmatojsongenerator.data.repositories.GetFigmaDataRepositoriesImpl
import com.example.figmatojsongenerator.domain.repositories.GetFigmaDataRepositories
import com.example.figmatojsongenerator.domain.usecase.GetFigmaDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FigmaModule {


    @Provides
    @Singleton
    fun provideFigmaDataRepositories(
        figmaRestApi: FigmaRestApi
    ) : GetFigmaDataRepositories {
        return GetFigmaDataRepositoriesImpl(figmaRestApi)
    }
    @Provides
    @Singleton
    fun provideFigmaDataUseCase(figmaDataRepositories: GetFigmaDataRepositories) : GetFigmaDataUseCase{
        return GetFigmaDataUseCase(figmaDataRepositories)
    }

}