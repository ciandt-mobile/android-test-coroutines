package com.ciandt.testcoroutines.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ciandt.testcoroutines.repository.CountRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class MainViewModel : ViewModel() {

    private val repository = CountRepository()

    private val _count = MutableLiveData<Int>().apply { value = 0 }

    val count: LiveData<Int>
        get() = _count

    fun up() {
        launch(UI) {
            println("test: ViewModel UP ${Thread.currentThread().name}")
            _count.value = repository.up(_count.value!!).await()
        }
    }

    fun down() {
        launch(UI) {
            println("test: ViewModel DOWN ${Thread.currentThread().name}")
            _count.value = repository.down(_count.value!!).await()
        }
    }
}