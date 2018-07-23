package com.ciandt.testcoroutines.repository

import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class CountRepositoryTest {

    @Test
    fun increase_shouldIncreaseCounting() = runBlocking {

        val repository = CountRepositoryImpl()

        var count = 0

        count = repository.increase(count).await()

        assertEquals(1, count)
    }

    @Test
    fun decrease_shouldDecreaseCounting() = runBlocking {

        val repository = CountRepositoryImpl()

        var count = 0

        count = repository.decrease(count).await()

        assertEquals(-1, count)
    }
}
