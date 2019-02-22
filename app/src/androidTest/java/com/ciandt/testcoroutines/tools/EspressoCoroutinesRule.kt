package com.ciandt.testcoroutines.tools

import android.support.test.espresso.IdlingRegistry
import com.ciandt.testcoroutines.infrastructure.coroutines.CoroutineExecutor
import com.ciandt.testcoroutines.infrastructure.coroutines.toCoroutineContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class EspressoCoroutinesRule : TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)

        IdlingRegistry.getInstance().register(CoroutineIdlingResource.default)

        CoroutineExecutor.instance.launchExecutor = { asyncContext, block ->
            CoroutineIdlingResource.default.run()

            CoroutineScope(asyncContext.toCoroutineContext()).launch(block = block)
                .invokeOnCompletion {
                    CoroutineIdlingResource.default.idle()
                }
        }

        CoroutineExecutor.instance.asyncExecutor = { asyncContext, block ->

            CoroutineIdlingResource.default.run()

            CoroutineScope(asyncContext.toCoroutineContext()).async(block = block).apply {
                invokeOnCompletion {
                    CoroutineIdlingResource.default.idle()
                }
            }
        }
    }

    override fun finished(description: Description?) {
        super.finished(description)
        CoroutineExecutor.instance.launchExecutor = null
        CoroutineExecutor.instance.asyncExecutor = null
        IdlingRegistry.getInstance().unregister(CoroutineIdlingResource.default)
    }
}