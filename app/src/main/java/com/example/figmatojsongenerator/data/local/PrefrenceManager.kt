package com.example.figmatojsongenerator.data.local

import kotlinx.coroutines.flow.Flow

interface PrefrenceManager {
    suspend fun saveToken(token: String)

    fun getToken(): Flow<String?>
}