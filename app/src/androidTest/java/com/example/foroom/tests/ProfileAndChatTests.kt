package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.data.Constants
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

@RunWith(AndroidJUnit4::class)
class ProfileAndChatTests {

    @get:Rule(order = 0)
    val clearUserDataRule = object : ExternalResource() {
        override fun before() = runBlocking {
            GlobalContext.get().get<ForoomUserDataStore>().clearUserData()
        }
    }

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()

    @Test
    fun changePasswordAndLoginWithNewPassword() {
        logIn(Constants.CURRENT_PASSWORD)
        changePassword(Constants.NEW_PASSWORD)
        logIn(Constants.NEW_PASSWORD)

        changePassword(Constants.CURRENT_PASSWORD)
        loginSteps.validateLoginScreenDisplayed()
    }

    @Test
    fun changeLanguageFromGeorgianToEnglishAndBack() {
        logIn(Constants.CURRENT_PASSWORD)
        profileSteps.openProfile()

        selectGeorgian()
        profileSteps.validateChangeLanguageLabelDisplayed(Constants.GEORGIAN_LABEL)

        selectEnglish()
        profileSteps.validateChangeLanguageLabelDisplayed(Constants.ENGLISH_LABEL)

        selectGeorgian()
        profileSteps.validateChangeLanguageLabelDisplayed(Constants.GEORGIAN_LABEL)
    }

    @Test
    fun createChatAndFindItInChatList() {
        val chatName = chatSteps.generateUniqueChatName()

        logIn(Constants.CURRENT_PASSWORD)

        chatSteps
            .openCreateChat()
            .enterChatName(chatName)
            .selectChatImage()
            .clickCreateChat()
            .validateChatTitleDisplayed(chatName)
            .closeChat()
            .searchChat(chatName)
            .validateChatCardDisplayed(chatName)
    }

    private fun logIn(password: String) {
        loginSteps
            .validateLoginScreenDisplayed()
            .enterUserName(Constants.TEST_USERNAME)
            .enterPassword(password)
            .clickLogIn()

        chatSteps.validateHomeScreenDisplayed()
    }

    private fun changePassword(newPassword: String) {
        profileSteps
            .openProfile()
            .clickChangePassword()
            .enterNewPassword(newPassword)
            .enterRepeatPassword(newPassword)
            .clickConfirm()
    }

    private fun selectGeorgian() {
        profileSteps
            .clickChangeLanguage()
            .selectGeorgian()
    }

    private fun selectEnglish() {
        profileSteps
            .clickChangeLanguage()
            .selectEnglish()
    }
}