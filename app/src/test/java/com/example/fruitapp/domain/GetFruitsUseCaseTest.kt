package com.example.fruitapp.domain

import com.example.fruitapp.data.repository.FruitRepository
import com.example.fruitapp.domain.model.FruitItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetFruitsUseCaseTest {

    /**
     * @RelaxedMockK crea instancias de otros llamados que se hagan en nuestro ambiente de pruebas.
     * @MockK no crea instancias por lo que se deberia andar con mas cuidado, e implementar por
     * nuestra cuenta esos llamados.
     */
    @RelaxedMockK
    private lateinit var fruitRepository: FruitRepository

    lateinit var getFruitsUseCase: GetFruitsUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getFruitsUseCase = GetFruitsUseCase(fruitRepository)
    }

    @Test
    fun `when the api doesn't return anything then get values from database`() = runBlocking {
        // Given (Las implementaciones o mocks necesarios)
        // coEvery se usa si las funciones a llamar trabajan con corrutinas.
        // En caso contrario se utilizaria "every".
        coEvery { fruitRepository.getAllFruitsFromApi() } returns emptyList()

        // When (Llamadas a los mocks para su ejecucion)
        getFruitsUseCase()

        // Then (Verificaciones)
        coVerify(exactly = 1) { fruitRepository.getAllFruitsFromDatabase() }
        coVerify(exactly = 0) { fruitRepository.clearFruits() }
        coVerify(exactly = 0) { fruitRepository.insertFruitOnDatabase(any()) }
    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        val fruitList =
            listOf(FruitItem(1, "genus", "name", "family", "order", 0.0, 0.0, 0.0, 0, 0.0))
        coEvery { fruitRepository.getAllFruitsFromApi() } returns fruitList

        val response = getFruitsUseCase()

        coVerify(exactly = 1) { fruitRepository.clearFruits() }
        coVerify(exactly = 1) { fruitRepository.insertFruitsOnDatabase(any()) }
        coVerify(exactly = 0) { fruitRepository.getAllFruitsFromDatabase() }
        assert(fruitList == response)
    }
}