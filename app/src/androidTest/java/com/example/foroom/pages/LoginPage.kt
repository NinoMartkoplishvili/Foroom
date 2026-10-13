package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import com.example.design_system.R as DesignR

class LoginPage {
    val userNameInput: Matcher<View> = withId(R.id.userNameInput)
    val passwordInput: Matcher<View> = withId(R.id.passwordInput)
    val logInButton: Matcher<View> = withId(R.id.logInButton)

    val userNameField: Matcher<View> = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(userNameInput))
    val passwordField: Matcher<View> = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(passwordInput))

}