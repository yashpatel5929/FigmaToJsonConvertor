package com.example.figmatojsongenerator.presentation.screens.mainscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.figmatojsongenerator.domain.usecase.AuthRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepositoriesUseCase: AuthRepositoriesUseCase
) : ViewModel() {

    fun saveFigmaAuthToke(token : String) {
        viewModelScope.launch {
            authRepositoriesUseCase.invoke(token)
        }

    }
}