package com.example.fruitapp.di

import com.example.fruitapp.data.remote.FruitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Los @Module son los modulos que proveen dependencias. @InstallIn es para administrar el alcance
 * del modulo.
 */
@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    private const val BASE_URL = "https://www.fruityvice.com/api/fruit/"

    /**
     * Estas son las dependencias que vamos a proveer, retrofit y fruitApi, cada vez que se inyecta
     * una de estas dependencias se crea una nueva instancia, lo cual consumiria muchos recursos,
     * por lo que marcamos el recurso que vamos a proveer como @Singleton.
     */

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Al proveer la dependencia "provideFruitApi" automaticamente se pedira provideRetrofit por
     * estar pidiendo retrofit, por lo que podriamos ahora inyectar una "interfaz" como cualquier
     * otra clase.
     *
     * Al hacer en alguna clase un:
     * @Inject contructor(private val api: FruitApi)
     * Nos retornaria una inyeccion de dependencia de retrofit para FruitApi, pudiendo asi usar los
     * endpoint que creamos.
     */

    @Singleton
    @Provides
    fun provideFruitApi(retrofit: Retrofit): FruitApi {
        return retrofit.create(FruitApi::class.java)
    }
}