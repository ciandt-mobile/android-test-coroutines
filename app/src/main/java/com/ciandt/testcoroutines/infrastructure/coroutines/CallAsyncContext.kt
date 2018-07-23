package com.ciandt.testcoroutines.infrastructure.coroutines

import kotlin.coroutines.experimental.CoroutineContext

sealed class CallAsyncContext(val context: CoroutineContext)
object UI : CallAsyncContext(kotlinx.coroutines.experimental.android.UI)
object CommonPool : CallAsyncContext(kotlinx.coroutines.experimental.CommonPool)