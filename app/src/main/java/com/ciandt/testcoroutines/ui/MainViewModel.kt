package com.ciandt.testcoroutines.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ciandt.testcoroutines.infrastructure.coroutines.callAsync
import com.ciandt.testcoroutines.repository.CountRepository
import com.ciandt.testcoroutines.repository.RepositoriesInjector
import kotlinx.coroutines.delay

class MainViewModel(private val repository: CountRepository = RepositoriesInjector.countRepository) :
    ViewModel() {

    private val _count = MutableLiveData<Int>().apply { value = 0 }

    val count: LiveData<Int>
        get() = _count

    fun increase() {
        callAsync {
            _count.value = repository.increase(_count.value!!).await()
        }
    }

    fun decrease() {
        callAsync {
            _count.value = repository.decrease(_count.value!!).await()
        }
    }
}