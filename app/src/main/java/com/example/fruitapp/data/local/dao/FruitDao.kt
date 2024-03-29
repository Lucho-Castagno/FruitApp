package com.example.fruitapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fruitapp.data.local.entities.FruitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FruitDao {

    @Query("SELECT * FROM fruit_table ORDER BY name ASC")
    suspend fun getAllFruits(): List<FruitEntity>

    @Query("SELECT * FROM fruit_table WHERE id = :id")
    suspend fun getFruit(id: Int): FruitEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFruit(fruit: FruitEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFruits(fruits: List<FruitEntity>)

    @Query("DELETE FROM fruit_table")
    suspend fun deleteAllFruits()

}