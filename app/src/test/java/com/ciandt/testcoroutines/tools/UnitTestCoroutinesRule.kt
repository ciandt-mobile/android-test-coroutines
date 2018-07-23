package com.ciandt.testcoroutines.tools

import com.ciandt.testcoroutines.infrastructure.coroutines.CallAsyncExecutor
import kotlinx.coroutines.experimental.runBlocking
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class UnitTestCoroutinesRule : TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)
        CallAsyncExecutor.instance.delegate = { asyncContext, block ->
            runBlocking(asyncContext.context, block = block)
        }
    }

    override fun finished(description: Description?) {
        super.finished(description)
        CallAsyncExecutor.instance.delegate = null
    }
}