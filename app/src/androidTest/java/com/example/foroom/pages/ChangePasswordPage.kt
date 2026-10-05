package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.R as DesignR
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class ChangePasswordPage {
    private val contentContainer: Matcher<View> = withId(DesignR.id.contentContainer)
    val passwordInput: Matcher<View> = allOf(withId(R.id.passwordInput), isDescendantOfA(contentContainer))
    val repeatPasswordInput: Matcher<View> = allOf(withId(R.id.repeatPasswordInput), isDescendantOfA(contentContainer))

    val passwordField: Matcher<View> = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(passwordInput))
    val repeatPasswordField: Matcher<View> = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(repeatPasswordInput))
    val confirmButton: Matcher<View> = withId(DesignR.id.actionButton)
}