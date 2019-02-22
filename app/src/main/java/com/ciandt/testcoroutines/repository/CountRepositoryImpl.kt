package com.ciandt.testcoroutines.repository

import com.ciandt.testcoroutines.infrastructure.coroutines.callAsync
import kotlinx.coroutines.delay

class CountRepositoryImpl : CountRepository {

    override fun increase(count: Int) = callAsync<Int> {
        delay(1000)
        count + 1
    }

    override fun decrease(count: Int) = callAsync<Int> {
        delay(1000)
        count - 1
    }
}