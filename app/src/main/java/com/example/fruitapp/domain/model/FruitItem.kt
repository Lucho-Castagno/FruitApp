package com.example.fruitapp.domain.model

import com.example.fruitapp.data.local.entities.FruitEntity
import com.example.fruitapp.data.remote.model.FruitModel

data class FruitItem(
    val id: Int = 0,
    val genus: String,
    val name: String,
    val family: String,
    val carbohydrates: Double,
    val protein: Double,
    val fat: Double,
    val calories: Int,
    val sugar: Double
)

/**
 * Mapper de FruitModel a FruitItem.
 */
fun FruitModel.toDomain() = FruitItem(
    id = id,
    genus = genus,
    name = name,
    family = family,
    carbohydrates = nutritionModel.carbohydrates,
    fat = nutritionModel.fat,
    calories = nutritionModel.calories,
    protein = nutritionModel.protein,
    sugar = nutritionModel.sugar
)

/**
 * Mapper de FruitEntity a FruitItem.
 */
fun FruitEntity.toDomain() = FruitItem(
    id = id,
    genus = genus,
    name = name,
    family = family,
    carbohydrates = carbohydrates,
    fat = fat,
    calories = calories,
    protein = protein,
    sugar = sugar
)
