package com.example.fruitapp.di

import android.content.Context
import androidx.room.Room
import com.example.fruitapp.data.local.FruitDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    private const val FRUIT_DATABASE_NAME = "fruit_database"

    /**
     * Este LocalModule tendria la misma explicacion que RemoteModule, solo que aqui lo hacemos para
     * la base de datos.
     */

    @Singleton
    @Provides
    fun provideFruitDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, FruitDatabase::class.java, FRUIT_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideFruitDao(db: FruitDatabase) = db.getFruitDao()
}