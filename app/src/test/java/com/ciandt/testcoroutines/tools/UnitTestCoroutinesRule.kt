package com.ciandt.testcoroutines.tools

import com.ciandt.testcoroutines.infrastructure.coroutines.CoroutineExecutor
import kotlinx.coroutines.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class UnitTestCoroutinesRule : TestWatcher() {

    @ExperimentalCoroutinesApi
    override fun starting(description: Description?) {
        super.starting(description)

        CoroutineExecutor.instance.launchExecutor = { _, block ->
            runBlocking(Dispatchers.Default, block = block)
        }

        CoroutineExecutor.instance.asyncExecutor = { _, block ->
            CoroutineScope(Dispatchers.Default).async(block = block)
        }
    }

    override fun finished(description: Description?) {
        super.finished(description)
        CoroutineExecutor.instance.launchExecutor = null
        CoroutineExecutor.instance.asyncExecutor = null
    }
}