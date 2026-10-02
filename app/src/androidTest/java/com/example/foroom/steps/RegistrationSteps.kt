package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.Constants.TIMEOUT
import com.example.foroom.pages.RegistrationPage

class RegistrationSteps {
    private val registrationPage = RegistrationPage()

    fun generateUniqueUserName() = "test${System.currentTimeMillis()}"

    fun validateRegistrationScreenDisplayed() = apply {
        onView(registrationPage.repeatPasswordInput).waitUntilVisible(TIMEOUT).check(matches(isDisplayed()))
    }

    fun enterUserName(userName: String) = apply { onView(registrationPage.userNameField).input(userName) }

    fun enterPassword(password: String) = apply { onView(registrationPage.passwordField).input(password) }

    fun enterRepeatPassword(password: String) = apply { onView(registrationPage.repeatPasswordField).input(password) }

    fun selectAvatar(position: Int = 1) = apply {
        onView(registrationPage.loadedAvatarList).waitUntilVisible(TIMEOUT)
        registrationPage.avatarItem.tap(byPosition = position)
    }

    fun clickSignUp() = apply { onView(registrationPage.signUpButton).tap() }

    fun validateHomeScreenDisplayed() = apply {
        onView(registrationPage.navBar).waitUntilVisible(TIMEOUT).check(matches(isDisplayed()))
    }
}