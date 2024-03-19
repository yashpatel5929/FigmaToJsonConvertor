package com.example.figmatojsongenerator.data.repositories

import android.util.Log
import com.example.figmatojsongenerator.data.remote.FigmaRestApi
import com.example.figmatojsongenerator.data.remote.dto.FigmaResponseDto
import com.example.figmatojsongenerator.data.remote.mappers.DtoToDomainMapping
import com.example.figmatojsongenerator.domain.model.ScreenData
import com.example.figmatojsongenerator.domain.repositories.GetFigmaDataRepositories
import com.example.figmatojsongenerator.presentation.utils.Resource

class GetFigmaDataRepositoriesImpl(
    private val figmaRestApi: FigmaRestApi
) : GetFigmaDataRepositories {
    var mapperToDomain : FigmaResponseDto ?= null
    override suspend fun getFigmaData(filePath: String): Resource<FigmaResponseDto> {

        return try {
            val response = figmaRestApi.getFigmaData(filePath)
            if (response.isSuccessful) {
                val responseBody = response.body()!!
                if (responseBody.status == 403 || responseBody.status == 404 || responseBody.status == 402) {
                    Resource.Error(null, responseBody.err ?: "")
                } else {
                    mapperToDomain = responseBody
                    Resource.Success(responseBody, null)
                }
            } else {
                Resource.Error(null, "something went wrong")
            }

        } catch (e: Exception) {
            Log.e("TAG", "getFigmaData: ${e.message}")
            return Resource.Error(null, "${e.message}")

        }


    }

    override suspend fun getJson(name: String): Resource<ScreenData?> {
        val obj = mapperToDomain?.let { DtoToDomainMapping(it) }
        obj?.mapData(name)
        return Resource.Success(data = obj?.screenData!!)
    }
}