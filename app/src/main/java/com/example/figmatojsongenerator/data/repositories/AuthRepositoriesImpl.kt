package com.example.figmatojsongenerator.data.repositories

import com.example.figmatojsongenerator.data.local.PrefrenceManager
import com.example.figmatojsongenerator.domain.repositories.AuthRepositories

class AuthRepositoriesImpl (private  val  prefrenceManager: PrefrenceManager): AuthRepositories {
    override suspend fun saveToken(token : String) {
        prefrenceManager.saveToken(token)
    }
}