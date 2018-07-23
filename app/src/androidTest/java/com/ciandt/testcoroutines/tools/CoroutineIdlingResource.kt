package com.ciandt.testcoroutines.tools

import android.support.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicBoolean

class CoroutineIdlingResource private constructor() : IdlingResource {

    private val isIdleNow = AtomicBoolean(true)
    private var callback: IdlingResource.ResourceCallback? = null

    fun run() {
        isIdleNow.set(false)
    }

    fun idle() {
        isIdleNow.set(true)
        callback?.onTransitionToIdle()
    }

    override fun getName(): String = this::class.java.name

    override fun isIdleNow() = isIdleNow.get()

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }

    companion object {
        val default = CoroutineIdlingResource()
    }
}