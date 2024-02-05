package com.example.fruitapp.data.remote

import com.example.fruitapp.data.remote.model.FruitModel
import com.example.fruitapp.data.remote.model.NutritionModel
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

    suspend fun getFruit(id: Int): FruitModel {
        return withContext(Dispatchers.IO) {
            val response = api.getFruitById(id)
            /**
             * Retocar esta parte con manejo de excepciones/errores. Ya que enviar un objeto creado
             * a mano con valores por defecto me parece la peor.
             */
            response.body() ?: FruitModel(0, "", "", "", "",
                NutritionModel(0.0, 0.0, 0.0, 0, 0.0))
        }
    }
}