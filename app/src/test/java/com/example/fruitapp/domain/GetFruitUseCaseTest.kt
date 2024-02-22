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

class GetFruitUseCaseTest {

    @RelaxedMockK
    private lateinit var fruitRepository: FruitRepository

    lateinit var getFruitUseCase: GetFruitUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getFruitUseCase = GetFruitUseCase(fruitRepository)
    }

    @Test
    fun `when needed a specific fruit then get value from database`() = runBlocking {
        val fruit = FruitItem(1, "genus", "name", "family", "order", 0.0, 0.0, 0.0, 0, 0.0)
        coEvery { fruitRepository.getFruitFromDatabase(1) } returns fruit

        val response = getFruitUseCase(fruit.id)

        coVerify(exactly = 1) { fruitRepository.getFruitFromDatabase(fruit.id) }
        assert(response == fruit)
    }
}