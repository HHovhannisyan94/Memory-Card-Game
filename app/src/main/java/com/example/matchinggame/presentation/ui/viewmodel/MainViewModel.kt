package com.example.matchinggame.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matchinggame.usecase.GetImageUseCase
import com.example.matchinggame.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getImageUseCase: GetImageUseCase
) : ViewModel() {
    private val _response: MutableSharedFlow<NetworkResult<ResponseBody>> =
        MutableSharedFlow(replay = 0)
    val response: MutableSharedFlow<NetworkResult<ResponseBody>> = _response

    fun fetchImgResponse() = viewModelScope.launch {
        getImageUseCase.getImages().collect { values ->
            _response.emit(values)
        }
    }
}