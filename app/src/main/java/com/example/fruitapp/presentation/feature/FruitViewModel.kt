package com.example.fruitapp.presentation.feature

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fruitapp.domain.CreateFruitUseCase
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
    private val getFruitUseCase: GetFruitUseCase,
    private val createFruitUseCase: CreateFruitUseCase
) : ViewModel() {

    val fruitsModel = MutableLiveData<List<FruitItem>>()
    val isLoading = MutableLiveData<Boolean>()

    private fun insertFruit(fruit: FruitItem) {
        viewModelScope.launch {
            createFruitUseCase(fruit)
        }
    }

    private fun getNewFruitEntry(fruitName: String, fruitFamily: String, fruitGenus: String,
                                 fruitCalories: String, fruitCarbohydrates: String, fruitFat: String,
                                 fruitProtein: String, fruitSugar: String): FruitItem {
        return FruitItem(genus = fruitGenus, name = fruitName, family = fruitFamily,
            carbohydrates = fruitCarbohydrates.toDouble(), protein =  fruitProtein.toDouble(),
            fat = fruitFat.toDouble(), calories = fruitCalories.toInt(),
            sugar = fruitSugar.toDouble())
    }

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

    fun isEntryValid(fruitName: String, fruitFamily: String, fruitGenus: String,
                      fruitCalories: String, fruitCarbohydrates: String, fruitFat: String,
                      fruitProtein: String, fruitSugar: String): Boolean {
        return !(fruitName.isBlank() || fruitFamily.isBlank() || fruitGenus.isBlank() ||
                fruitCalories.isBlank() || fruitCarbohydrates.isBlank() || fruitFat.isBlank() ||
                fruitProtein.isBlank() || fruitSugar.isBlank())
    }

    fun addNewFruit(fruitName: String, fruitFamily: String, fruitGenus: String,
                    fruitCalories: String, fruitCarbohydrates: String, fruitFat: String,
                    fruitProtein: String, fruitSugar: String) {
        val newFruit = getNewFruitEntry(
            fruitName, fruitFamily, fruitGenus, fruitCalories, fruitCarbohydrates, fruitFat,
            fruitProtein, fruitSugar
        )
        insertFruit(newFruit)
    }
}