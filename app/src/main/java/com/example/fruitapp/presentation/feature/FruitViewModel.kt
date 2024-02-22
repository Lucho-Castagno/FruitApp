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
    val selectedFruit = MutableLiveData<FruitItem>()
    val isLoading = MutableLiveData<Boolean>()
    val fruitFormViewState = MutableLiveData<FruitFormViewState>()

    private fun insertFruit(fruit: FruitItem) {
        viewModelScope.launch {
            createFruitUseCase(fruit)
        }
    }

    private fun getNewFruitEntry(fruitName: String, fruitFamily: String, fruitGenus: String,
                                 fruitOrder: String, fruitCalories: String,
                                 fruitCarbohydrates: String, fruitFat: String, fruitProtein: String,
                                 fruitSugar: String): FruitItem {
        return FruitItem(genus = fruitGenus, name = fruitName, family = fruitFamily,
            order = fruitOrder, carbohydrates = fruitCarbohydrates.toDouble(),
            protein =  fruitProtein.toDouble(), fat = fruitFat.toDouble(),
            calories = fruitCalories.toInt(), sugar = fruitSugar.toDouble())
    }

    fun onCreate(adapter: FruitListAdapter) {
        viewModelScope.launch {
            isLoading.value = true
            val result = getFruitsUseCase()
            if (result.isNotEmpty()) {
                fruitsModel.value = result
                adapter.submitList(result)
                isLoading.value = false
            }
        }
    }

    fun retrieveFruit(id: Int) {
        viewModelScope.launch {
            try {
                selectedFruit.value = getFruitUseCase(id)
                Log.i("FRUIT__ITEM_RETRIEVE_ITEM", selectedFruit.value.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun isEntryValid(fruitName: String, fruitFamily: String, fruitGenus: String, fruitOrder: String,
                      fruitCalories: String, fruitCarbohydrates: String, fruitFat: String,
                      fruitProtein: String, fruitSugar: String): Boolean {
        return !(fruitName.isBlank() || fruitFamily.isBlank() || fruitGenus.isBlank() ||
                fruitOrder.isBlank() || fruitCalories.isBlank() || fruitCarbohydrates.isBlank() ||
                fruitFat.isBlank() || fruitProtein.isBlank() || fruitSugar.isBlank())
    }

    fun addNewFruit(fruitName: String, fruitFamily: String, fruitGenus: String, fruitOrder: String,
                    fruitCalories: String, fruitCarbohydrates: String, fruitFat: String,
                    fruitProtein: String, fruitSugar: String) {
        val newFruit = getNewFruitEntry(
            fruitName, fruitFamily, fruitGenus, fruitOrder, fruitCalories, fruitCarbohydrates,
            fruitFat, fruitProtein, fruitSugar
        )
        insertFruit(newFruit)
    }
}