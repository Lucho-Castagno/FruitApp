package com.example.fruitapp.domain

import android.util.Log
import com.example.fruitapp.data.local.entities.toDatabase
import com.example.fruitapp.data.repository.FruitRepository
import com.example.fruitapp.domain.model.FruitItem
import javax.inject.Inject

class GetFruitsUseCase @Inject constructor(
    private val repository: FruitRepository
) {
    suspend operator fun invoke(): List<FruitItem> {
        val fruits = repository.getAllFruitsFromApi()
        return if (fruits.isNotEmpty()) {
            /**
             * repository.clearFruits() sirve para eliminar la base de datos al momento de volver
             * a pedir los datos y guardarlos en la bd, para no tener items repetidos.
             *
             * Solo por esta vez, el delete es un DELETE ALL FROM DATABASE, cuando en lo general se
             * tendria en cuenta un WHERE.
             */
            Log.i("GET_FRUIT_USE_CASE", fruits.toString())
            repository.clearFruits()
            repository.insertFruits(fruits.map { it.toDatabase() })
            fruits
        } else {
            repository.getAllFruitsFromDatabase()
        }
    }
}