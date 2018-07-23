package com.ciandt.testcoroutines.repository

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay

class CountRepositoryImpl : CountRepository {

    override fun increase(count: Int) = async(CommonPool) {
        delay(500)
        count + 1
    }

    override fun decrease(count: Int) = async(CommonPool) {
        delay(500)
        count - 1
    }
}