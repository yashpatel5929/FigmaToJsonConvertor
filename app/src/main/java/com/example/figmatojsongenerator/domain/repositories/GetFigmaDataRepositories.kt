package com.example.figmatojsongenerator.domain.repositories

import com.example.figmatojsongenerator.data.remote.dto.FigmaResponseDto
import com.example.figmatojsongenerator.domain.model.ScreenData
import com.example.figmatojsongenerator.presentation.utils.Resource

interface GetFigmaDataRepositories {
    suspend fun getFigmaData(filePath : String) : Resource<FigmaResponseDto>
    suspend fun getJson(name : String) : Resource<ScreenData?>
}