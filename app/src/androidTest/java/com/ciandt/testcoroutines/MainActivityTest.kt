package com.ciandt.testcoroutines

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.ciandt.testcoroutines.infrastructure.coroutines.callAsync
import com.ciandt.testcoroutines.repository.CountRepository
import com.ciandt.testcoroutines.repository.RepositoriesInjector
import com.ciandt.testcoroutines.tools.EspressoCoroutinesRule
import com.ciandt.testcoroutines.ui.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, true, false)

    @Rule
    @JvmField
    val coroutinesRule = EspressoCoroutinesRule()

    private val repository = Mockito.mock(CountRepository::class.java)

    @Before
    fun setup() {
        RepositoriesInjector.countRepository = repository
        activityRule.launchActivity(null)
    }

    @Test
    fun increaseButton_shouldIncreaseCounting() {
        `when`(repository.increase(0)).thenReturn(callAsync<Int> { 1 })
        onView(withId(R.id.btnIncrease)).perform(click())
        onView(withText("1")).check(matches(isDisplayed()))
    }

    @Test
    fun increaseButton_shouldDecreaseCounting() {
        `when`(repository.decrease(0)).thenReturn(callAsync<Int> { -1 })
        onView(withId(R.id.btnDecrease)).perform(click())
        onView(withText("-1")).check(matches(isDisplayed()))
    }
}
