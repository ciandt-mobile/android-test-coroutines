package com.ciandt.testcoroutines.ui

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.ciandt.testcoroutines.tools.UnitTestCoroutinesRule
import com.ciandt.testcoroutines.tools.verify
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val coroutinesRule = UnitTestCoroutinesRule()

    @Test
    fun increase_shouldIncreaseCounting() = runBlocking {
        val viewModel = MainViewModel()
        viewModel.increase()

        viewModel.count.verify {
            assertEquals(1, it)
        }
    }

    @Test
    fun decrease_shouldDecreaseCounting() = runBlocking {
        val viewModel = MainViewModel()
        viewModel.decrease()

        viewModel.count.verify {
            assertEquals(-1, it)
        }
    }
}