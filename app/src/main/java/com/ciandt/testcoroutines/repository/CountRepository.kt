package com.ciandt.testcoroutines.repository

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay

class CountRepository {

    fun increase(count: Int) = async(CommonPool) {
        delay(500)
        count + 1
    }

    fun decrease(count: Int) = async(CommonPool) {
        delay(500)
        count - 1
    }
}