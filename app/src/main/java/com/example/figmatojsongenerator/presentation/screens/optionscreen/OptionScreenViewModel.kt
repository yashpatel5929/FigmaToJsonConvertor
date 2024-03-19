package com.example.figmatojsongenerator.presentation.screens.optionscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.figmatojsongenerator.domain.model.ScreenData
import com.example.figmatojsongenerator.domain.usecase.GetFigmaDataUseCase
import com.example.figmatojsongenerator.presentation.utils.Resource
import com.example.figmatojsongenerator.presentation.utils.ScreenType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class OptionScreenViewModel @Inject constructor(
    private val getFigmaDataUseCase: GetFigmaDataUseCase
) : ViewModel() {

    private val _isLoading  : MutableStateFlow<Boolean> = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    private val _jsonValue : MutableStateFlow<ScreenData?> = MutableStateFlow(null)
    val jsonValue = _jsonValue.asStateFlow()

    fun getFigmaData(filePath : String) {
        viewModelScope.launch {
            _isLoading.emit(true)
            when(val result = getFigmaDataUseCase.invoke(filePath)){
                is Resource.Success -> {
                    _isLoading.emit(false)
                    Log.d("ApiCall", "getFigmaData: success ${result.data}")
                }
                is Resource.Error ->{
                    _isLoading.emit(false)
                    Log.d("ApiCall", "getFigmaData: fail")
                }
            }
        }

    }

    fun getJson(name : String){
        viewModelScope.launch {
            when(val result = getFigmaDataUseCase.getJson(name)){
                is Resource.Success -> {
                    _jsonValue.emit(result.data)
                }
                else ->{}
            }
        }
    }
}