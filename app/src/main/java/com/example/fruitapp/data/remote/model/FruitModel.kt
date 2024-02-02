package com.example.fruitapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class FruitModel(
    val id: Int,
    val genus: String,
    val name: String,
    val family: String,
    val order: String,
    @SerializedName("nutritions") val nutritionModel: NutritionModel
)
