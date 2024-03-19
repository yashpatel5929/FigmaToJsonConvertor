package com.example.figmatojsongenerator.domain.usecase

import com.example.figmatojsongenerator.data.local.PrefrenceManager
import com.example.figmatojsongenerator.domain.repositories.AuthRepositories

class AuthRepositoriesUseCase (private val authRepositories: AuthRepositories) {
 suspend operator fun invoke(token : String) {
    authRepositories.saveToken(token)
 }
}