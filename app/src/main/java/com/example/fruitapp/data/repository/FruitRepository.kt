package com.example.fruitapp.data.repository

import android.util.Log
import androidx.lifecycle.asLiveData
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

    suspend fun getFruitFromDatabase(id: Int): FruitItem {
        val response: FruitEntity = fruitDao.getFruit(id)
        return response.toDomain()
    }

    suspend fun insertFruitsOnDatabase(fruits: List<FruitEntity>) {
        fruitDao.insertAllFruits(fruits)
    }

    suspend fun insertFruitOnDatabase(fruit: FruitEntity) {
        fruitDao.insertFruit(fruit)
    }

    suspend fun insertFruitOnApi(fruit: FruitModel) {
        //api.putFruit()
    }

    suspend fun clearFruits() {
        fruitDao.deleteAllFruits()
    }
}