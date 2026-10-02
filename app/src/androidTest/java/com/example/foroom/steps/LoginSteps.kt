package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.Constants.TIMEOUT
import com.example.foroom.pages.LoginPage

class LoginSteps {
    private val loginPage = LoginPage()

    fun validateLoginScreenDisplayed() = apply {
        onView(loginPage.logInButton).waitUntilVisible(TIMEOUT).check(matches(isDisplayed()))
    }

    fun enterUserName(userName: String) = apply {
        onView(loginPage.userNameField).input(userName)
    }

    fun enterPassword(password: String) = apply {
        onView(loginPage.passwordField).input(password)
    }

    fun clickLogIn() = apply {
        onView(loginPage.logInButton).tap()
    }

    fun clickSignUp() = apply {
        onView(loginPage.signUpButton).tap()
    }

    fun validateUserNameErrorDisplayed() = apply {
        onView(loginPage.userNameError).waitUntilVisible(TIMEOUT).check(matches(isDisplayed()))
    }

    fun validatePasswordErrorDisplayed() = apply {
        onView(loginPage.passwordError).waitUntilVisible(TIMEOUT).check(matches(isDisplayed()))
    }
    fun validateUserNameErrorNotDisplayed() = apply {
        onView(loginPage.userNameError).check(doesNotExist())
    }
}