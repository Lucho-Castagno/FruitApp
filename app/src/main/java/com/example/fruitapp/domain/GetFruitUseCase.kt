package com.example.fruitapp.domain

import android.util.Log
import com.example.fruitapp.data.repository.FruitRepository
import com.example.fruitapp.domain.model.FruitItem
import javax.inject.Inject

class GetFruitUseCase @Inject constructor(
    private val repository: FruitRepository
) {

    suspend operator fun invoke(id: Int): FruitItem {
        return repository.getFruitFromDatabase(id)
    }
}