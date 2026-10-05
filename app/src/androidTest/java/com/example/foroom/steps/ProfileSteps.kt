package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.Constants.TIMEOUT
import com.example.foroom.pages.ChangeLanguagePage
import com.example.foroom.pages.ChangePasswordPage
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.ProfilePage

class ProfileSteps {
    private val chatsPage = ChatsPage()
    private val profilePage = ProfilePage()
    private val changePasswordPage = ChangePasswordPage()
    private val changeLanguagePage = ChangeLanguagePage()

    fun openProfile() = apply {
        onView(chatsPage.homeNavigationProfile).tap()
    }

    fun clickChangePassword() = apply {
        onView(profilePage.changePasswordItem).tap()
        onView(changePasswordPage.confirmButton).waitUntilVisible(TIMEOUT)
    }

    fun enterNewPassword(password: String) = apply {
        onView(changePasswordPage.passwordField).input(password)
    }

    fun enterRepeatPassword(password: String) = apply {
        onView(changePasswordPage.repeatPasswordField).input(password)
    }

    fun clickConfirm() = apply {
        onView(changePasswordPage.confirmButton).tap()
    }


    fun clickChangeLanguage() = apply {
        onView(profilePage.changeLanguageItem).tap()
        onView(changeLanguagePage.georgianButton).waitUntilVisible(TIMEOUT)
    }

    fun selectGeorgian() = apply {
        onView(changeLanguagePage.georgianButton).tap()
    }

    fun selectEnglish() = apply {
        onView(changeLanguagePage.englishButton).tap()
    }

    fun validateChangeLanguageLabelDisplayed(label: String) = apply {
        onView(profilePage.changeLanguageLabel(label)).waitUntilVisible(TIMEOUT).check(matches(isDisplayed()))
    }
}