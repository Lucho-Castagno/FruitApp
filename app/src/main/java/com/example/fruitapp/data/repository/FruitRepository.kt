package com.example.fruitapp.data.repository

import com.example.fruitapp.data.local.dao.FruitDao
import com.example.fruitapp.data.local.entities.FruitEntity
import com.example.fruitapp.data.remote.model.FruitModel
import com.example.fruitapp.data.remote.FruitService
import com.example.fruitapp.domain.model.FruitItem
import com.example.fruitapp.domain.model.toDomain
import javax.inject.Inject

class FruitRepository @Inject constructor(
    private val api: FruitService,
    private val fruitDao: FruitDao
) {
    suspend fun getAllFruitsFromApi(): List<FruitItem> {
        val response: List<FruitModel> = api.getFruits()
        return response.map { it.toDomain() }
    }

    suspend fun getAllFruitsFromDatabase(): List<FruitItem> {
        val response: List<FruitEntity> = fruitDao.getAllFruits()
        return response.map { it.toDomain() }
    }

    suspend fun  insertFruits(fruits: List<FruitEntity>) {
        fruitDao.insertAllFruits(fruits)
    }

    suspend fun clearFruits() {
        fruitDao.deleteAllFruits()
    }
}