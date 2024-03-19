package com.example.figmatojsongenerator.domain.usecase

import com.example.figmatojsongenerator.data.remote.dto.FigmaResponseDto
import com.example.figmatojsongenerator.domain.model.ScreenData
import com.example.figmatojsongenerator.domain.repositories.GetFigmaDataRepositories
import com.example.figmatojsongenerator.presentation.utils.Resource

class GetFigmaDataUseCase (
    private val figmaDataRepositories: GetFigmaDataRepositories
) {

    suspend operator fun invoke(filePath : String) : Resource<FigmaResponseDto> {
        return figmaDataRepositories.getFigmaData(filePath)
    }

    suspend fun getJson(screenName : String):Resource<ScreenData?> {
        return figmaDataRepositories.getJson(screenName)
    }

}