package com.example.fruitapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.fruitapp.data.local.dao.FruitDao
import com.example.fruitapp.data.local.entities.FruitEntity

@Database(entities = [FruitEntity::class], version = 1)
abstract class FruitDatabase : RoomDatabase() {

    abstract fun getFruitDao(): FruitDao

}