package com.example.fruitapp.presentation.feature

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruitapp.domain.GetFruitUseCase
import com.example.fruitapp.domain.GetFruitsUseCase
import com.example.fruitapp.domain.model.FruitItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.logging.Logger
import javax.inject.Inject

@HiltViewModel
class FruitViewModel @Inject constructor(
    private val getFruitsUseCase: GetFruitsUseCase,
    private val getFruitUseCase: GetFruitUseCase
) : ViewModel() {

    val fruitsModel = MutableLiveData<List<FruitItem>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.value = true
            val result = getFruitsUseCase()
            if (result.isNotEmpty()) {
                fruitsModel.value = result
                isLoading.value = false
            }
        }
    }

    fun retrieveFruit(id: Int): LiveData<FruitItem?> {
        val fruitLiveData = MutableLiveData<FruitItem?>()
        viewModelScope.launch {
            try {
                val fruitItem = getFruitUseCase(id)
                fruitLiveData.value = fruitItem
            } catch (e: Exception) {
                fruitLiveData.value = null
            }
        }
        return fruitLiveData
    }
}