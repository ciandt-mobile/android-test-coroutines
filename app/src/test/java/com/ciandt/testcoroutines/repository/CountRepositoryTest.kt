package com.ciandt.testcoroutines.repository

import com.ciandt.testcoroutines.infrastructure.coroutines.callAsync
import com.ciandt.testcoroutines.tools.UnitTestCoroutinesRule
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class CountRepositoryTest {

    @Rule
    @JvmField
    val coroutinesRule = UnitTestCoroutinesRule()

    @Test
    fun increase_shouldIncreaseCounting() = callAsync {

        val repository = CountRepositoryImpl()

        var count = 0

        count = repository.increase(count).await()

        assertEquals(1, count)
    }

    @Test
    fun decrease_shouldDecreaseCounting() = callAsync {

        val repository = CountRepositoryImpl()

        var count = 0

        count = repository.decrease(count).await()

        assertEquals(-1, count)
    }
}
