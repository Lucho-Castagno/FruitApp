package com.example.fruitapp.presentation.feature

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruitapp.domain.GetFruitsUseCase
import com.example.fruitapp.domain.model.FruitItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.logging.Logger
import javax.inject.Inject

@HiltViewModel
class FruitViewModel @Inject constructor(
    private val getFruitsUseCase: GetFruitsUseCase,
) : ViewModel() {

    val fruitsModel = MutableLiveData<List<FruitItem>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.value = true
            val result = getFruitsUseCase()
            Log.i("VIEW_MODEL", result.toString())
            if (!result.isNullOrEmpty()) {
                fruitsModel.value = result
                Log.i("VIEW_MODEL_FRUIT_MODEL", fruitsModel.value.toString())
                isLoading.value = false
            }
        }
    }
}