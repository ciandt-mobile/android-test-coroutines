package com.ciandt.testcoroutines.tools

import android.support.test.espresso.IdlingRegistry
import com.ciandt.testcoroutines.infrastructure.coroutines.CallAsyncExecutor
import kotlinx.coroutines.experimental.launch
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class EspressoCoroutinesRule : TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)

        IdlingRegistry.getInstance().register(CoroutineIdlingResource.default)

        CallAsyncExecutor.instance.delegate = { asyncContext, block ->
            launch(asyncContext.context) {
                CoroutineIdlingResource.default.run()
                launch(asyncContext.context, block = block).join()
                CoroutineIdlingResource.default.idle()
            }
        }
    }

    override fun finished(description: Description?) {
        super.finished(description)
        CallAsyncExecutor.instance.delegate = null
        IdlingRegistry.getInstance().unregister(CoroutineIdlingResource.default)
    }
}