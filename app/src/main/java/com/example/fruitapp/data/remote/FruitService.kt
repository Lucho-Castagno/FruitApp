package com.example.fruitapp.data.remote

import com.example.fruitapp.data.remote.model.FruitModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FruitService @Inject constructor(
    private val api: FruitApi
) {
    suspend fun getFruits(): List<FruitModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllFruits()
            response.body() ?: emptyList()
        }
    }
}