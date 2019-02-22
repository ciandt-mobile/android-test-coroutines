package com.ciandt.testcoroutines.repository

import kotlinx.coroutines.Deferred

interface CountRepository {
    fun increase(count: Int): Deferred<Int>
    fun decrease(count: Int): Deferred<Int>
}