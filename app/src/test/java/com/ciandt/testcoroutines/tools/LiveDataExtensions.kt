package com.ciandt.testcoroutines.tools

import android.arch.lifecycle.LiveData
import junit.framework.Assert
import org.jetbrains.annotations.TestOnly
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@TestOnly
fun <T> LiveData<T>.verify(body: ((T?)-> Unit)? = null) {
    val latch = CountDownLatch(1)

    var verify = false

    this.observeForever {
        latch.countDown()
        verify = true
        body?.invoke(this.value)
    }

    latch.await(2, TimeUnit.SECONDS)

    if (!verify) {
        Assert.fail("LiveData observer  not triggered")
    }
}