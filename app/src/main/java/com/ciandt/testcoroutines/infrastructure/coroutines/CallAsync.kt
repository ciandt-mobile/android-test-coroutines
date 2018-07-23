package com.ciandt.testcoroutines.infrastructure.coroutines

import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.launch

typealias CoroutineExecutor = (CallAsyncContext, suspend CoroutineScope.() -> Unit) -> Unit

class CallAsyncExecutor private constructor() {

    private val defaultDelegate: CoroutineExecutor = { asyncContext, block ->
        launch(asyncContext.context, block = block)
    }

    var delegate: CoroutineExecutor? = null

    fun execute(
        asyncContext: CallAsyncContext = CommonPool,
        block: suspend CoroutineScope.() -> Unit
    ) {
        delegate?.let {
            it(asyncContext, block)
        } ?: run {
            defaultDelegate(asyncContext, block)
        }
    }

    companion object {
        var instance: CallAsyncExecutor = CallAsyncExecutor()
    }
}

fun callAsync(
    asyncContext: CallAsyncContext = CommonPool,
    block: suspend CoroutineScope.() -> Unit
) {
    CallAsyncExecutor.instance.execute(asyncContext, block)
}