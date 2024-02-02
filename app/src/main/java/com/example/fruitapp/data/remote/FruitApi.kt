package com.example.fruitapp.data.remote

import com.example.fruitapp.data.remote.model.FruitModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface FruitApi {
    @GET("all")
    suspend fun getAllFruits(): Response<List<FruitModel>>

    @GET("{id}")
    suspend fun getFruitById(@Path("id") fruitId: Int): Response<FruitModel>

    @PUT("")
    suspend fun addFruit(fruitModel: FruitModel)
}