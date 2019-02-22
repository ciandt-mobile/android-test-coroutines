package com.ciandt.testcoroutines.infrastructure.extensions

inline fun <reified T> Any.cast(): T = if (this !is T)
    throw ClassCastException("${this::class.java.name} cannot be cast to ${T::class.java.name}")
else this