package com.ciandt.testcoroutines.infrastructure.coroutines

import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

sealed class CallAsyncContext(val context: CoroutineContext)
object UI : CallAsyncContext(Unconfined)
object CommonPool : CallAsyncContext(Unconfined)