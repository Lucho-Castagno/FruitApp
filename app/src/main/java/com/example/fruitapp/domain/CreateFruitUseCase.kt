package com.example.fruitapp.domain

import com.example.fruitapp.data.local.entities.toDatabase
import com.example.fruitapp.data.repository.FruitRepository
import com.example.fruitapp.domain.model.FruitItem
import javax.inject.Inject

class CreateFruitUseCase @Inject constructor(
    private val repository: FruitRepository
) {

    suspend operator fun invoke(fruit: FruitItem) {
        // crear fruitItem -> fruitModel
        //repository.insertFruitOnApi(fruit.toModel())
    }
}