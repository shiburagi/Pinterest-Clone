package com.shiburagi.pinterestclone


import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.widget.NestedScrollView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        mainPage()
        viewImagePage()
        closing()

    }

    fun mainPage() {
        val scrollView = onView(
            allOf(
                instanceOf(NestedScrollView::class.java),
                isDisplayed()
            )
        )
        Thread.sleep(2000)
        scrollView.perform(swipeUp())
        Thread.sleep(5000)
        scrollView.perform(swipeUp())
        Thread.sleep(5000)

        val linearLayout = onView(
            allOf(
                childAtPosition(
                    childAtPosition(
                        withId(R.id.rightLayout),
                        9
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout.perform(click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

    }

    fun viewImagePage() {
        val scrollView = onView(
            allOf(
                instanceOf(NestedScrollView::class.java),
                withParent(instanceOf(CoordinatorLayout::class.java)),
                isDisplayed()
            )
        )
        Thread.sleep(2000)
        scrollView.perform(swipeUp())
        Thread.sleep(5000)
        scrollView.perform(swipeUp())
        Thread.sleep(5000)


        val linearLayout2 = onView(
            allOf(
                childAtPosition(
                    childAtPosition(
                        withId(R.id.leftLayout),
                        8
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        linearLayout2.perform(click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(5000)
    }

    fun closing() {

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            withClassName(`is`("com.google.android.material.appbar.AppBarLayout")),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(2000)

        val appCompatImageButton2 = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            withClassName(`is`("com.google.android.material.appbar.AppBarLayout")),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageButton2.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
