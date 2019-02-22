package com.ciandt.testcoroutines.infrastructure.coroutines

import kotlinx.coroutines.Dispatchers

sealed class CallAsyncContext
object Main : CallAsyncContext()
object IO : CallAsyncContext()
object Cpu : CallAsyncContext()

fun CallAsyncContext.toCoroutineContext() = when (this) {
        Main -> Dispatchers.Main
        IO -> Dispatchers.IO
        Cpu -> Dispatchers.Default
    }