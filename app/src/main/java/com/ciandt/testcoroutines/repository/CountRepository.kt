package com.ciandt.testcoroutines.repository

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay

class CountRepository {

    fun up(count: Int) = async(CommonPool) {
        delay(500)
        println("test: Repository UP ${Thread.currentThread().name}")
        count + 1
    }

    fun down(count: Int) = async(CommonPool) {
        delay(500)
        println("test: Repository DOWN ${Thread.currentThread().name}")
        count - 1
    }
}