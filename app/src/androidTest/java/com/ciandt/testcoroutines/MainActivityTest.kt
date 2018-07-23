package com.ciandt.testcoroutines

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.ciandt.testcoroutines.tools.EspressoCoroutinesRule
import com.ciandt.testcoroutines.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Rule
    @JvmField
    val coroutinesRule = EspressoCoroutinesRule()

    @Test
    fun increaseButton_shouldIncreaseCounting() {
        onView(withId(R.id.btnUp)).perform(click())
        onView(withText("1")).check(matches(isDisplayed()))
    }

    @Test
    fun increaseButton_shouldDecreaseCounting() {
        onView(withId(R.id.btnDown)).perform(click())
        onView(withText("-1")).check(matches(isDisplayed()))
    }
}
