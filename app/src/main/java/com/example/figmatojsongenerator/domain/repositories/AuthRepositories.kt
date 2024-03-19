package com.example.figmatojsongenerator.domain.repositories

interface AuthRepositories {
    suspend fun saveToken(token : String)
}