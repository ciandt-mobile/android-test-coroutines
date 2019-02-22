package com.ciandt.testcoroutines.infrastructure.coroutines

import com.ciandt.testcoroutines.infrastructure.extensions.cast
import kotlinx.coroutines.*

typealias CoroutineLaunchExecutor = (CallAsyncContext, suspend CoroutineScope.() -> Unit) -> Unit

typealias CoroutineAsyncExecutor<T> = (CallAsyncContext, suspend CoroutineScope.() -> T) -> Deferred<T>

class CoroutineExecutor private constructor() {

    var launchExecutor: CoroutineLaunchExecutor? = null

    var asyncExecutor: CoroutineAsyncExecutor<*>? = null

    private val defaultLaunchExecutor: CoroutineLaunchExecutor

    private val defaultAsyncExecutor: CoroutineAsyncExecutor<*>

    init {

        defaultLaunchExecutor = { asyncContext, block ->
            CoroutineScope(asyncContext.toCoroutineContext()).launch(block = block)
        }

        defaultAsyncExecutor = { asyncContext, block ->
            CoroutineScope(asyncContext.toCoroutineContext()).async(block = block)
        }
    }

    fun launch(
        asyncContext: CallAsyncContext = IO,
        block: suspend CoroutineScope.() -> Unit
    ) {
        launchExecutor?.let {
            it(asyncContext, block)
        } ?: run {
            defaultLaunchExecutor(asyncContext, block)
        }
    }

    fun <T> async(
        asyncContext: CallAsyncContext = IO,
        block: suspend CoroutineScope.() -> T
    ) = asyncExecutor?.invoke(asyncContext, block) ?: defaultAsyncExecutor(asyncContext, block)


    companion object {
        var instance: CoroutineExecutor = CoroutineExecutor()
    }
}

fun callAsync(
    asyncContext: CallAsyncContext = Main,
    block: suspend CoroutineScope.() -> Unit
) = CoroutineExecutor.instance.launch(asyncContext, block)

fun <T> callAsync(
    asyncContext: CallAsyncContext = IO,
    block: suspend CoroutineScope.() -> T
): Deferred<T> = CoroutineExecutor.instance.async(asyncContext, block).cast()