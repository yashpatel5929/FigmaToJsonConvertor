package com.example.figmatojsongenerator.data.remote

import com.example.figmatojsongenerator.data.remote.dto.FigmaResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FigmaRestApi {

    @GET("files/{filePath}")
    suspend fun getFigmaData(
        @Path("filePath") filePath : String
    ) : Response<FigmaResponseDto>

}