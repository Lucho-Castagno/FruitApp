package com.example.fruitapp.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fruitapp.domain.model.FruitItem

@Entity(tableName = "fruit_table")
data class FruitEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "genus")
    val genus: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "family")
    val family: String,
    @ColumnInfo(name = "carbohydrates")
    val carbohydrates: Double,
    @ColumnInfo(name = "protein")
    val protein: Double,
    @ColumnInfo(name = "fat")
    val fat: Double,
    @ColumnInfo(name = "calories")
    val calories: Int,
    @ColumnInfo(name = "sugar")
    val sugar: Double
)

fun FruitItem.toDatabase() = FruitEntity(
    id = id,
    genus = genus,
    name = name,
    family = family,
    carbohydrates = carbohydrates,
    protein = protein,
    fat = fat,
    calories = calories,
    sugar = sugar
)
