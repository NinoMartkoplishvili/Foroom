package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class ProfilePage {
    val changePasswordItem: Matcher<View> = withId(R.id.changePasswordItem)
    val changeLanguageItem: Matcher<View> = withId(R.id.changeLanguageItem)

    fun changeLanguageLabel(text: String): Matcher<View> = allOf(withText(text), isDescendantOfA(changeLanguageItem))
}