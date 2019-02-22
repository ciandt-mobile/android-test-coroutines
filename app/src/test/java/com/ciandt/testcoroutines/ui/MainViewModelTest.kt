package com.ciandt.testcoroutines.ui

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.ciandt.testcoroutines.infrastructure.coroutines.callAsync
import com.ciandt.testcoroutines.repository.CountRepository
import com.ciandt.testcoroutines.tools.UnitTestCoroutinesRule
import com.ciandt.testcoroutines.tools.verify
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Mock
    lateinit var repository: CountRepository

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coroutinesRule = UnitTestCoroutinesRule()

    @Test
    fun increase_shouldIncreaseCounting() = callAsync {
        `when`(repository.increase(0)).thenReturn(callAsync<Int> { 1 })

        val viewModel = MainViewModel(repository)
        viewModel.increase()

        viewModel.count.verify {
            assertEquals(1, it)
        }
    }

    @Test
    fun decrease_shouldDecreaseCounting() = callAsync {
        `when`(repository.decrease(0)).thenReturn(callAsync<Int> { -1 })

        val viewModel = MainViewModel(repository)
        viewModel.decrease()

        viewModel.count.verify {
            assertEquals(-1, it)
        }
    }
}