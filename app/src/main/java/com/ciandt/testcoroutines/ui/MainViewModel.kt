package com.ciandt.testcoroutines.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ciandt.testcoroutines.infrastructure.coroutines.UI
import com.ciandt.testcoroutines.infrastructure.coroutines.callAsync
import com.ciandt.testcoroutines.repository.CountRepository

class MainViewModel(private val repository: CountRepository = CountRepository()) : ViewModel() {

    private val _count = MutableLiveData<Int>().apply { value = 0 }

    val count: LiveData<Int>
        get() = _count

    fun increase() {
        callAsync(UI) {
            _count.value = repository.increase(_count.value!!).await()
        }
    }

    fun decrease() {
        callAsync(UI) {
            _count.value = repository.decrease(_count.value!!).await()
        }
    }
}