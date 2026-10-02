package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.data.Constants
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.RegistrationSteps
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

@RunWith(AndroidJUnit4::class)
class LoginAndRegistrationTests {

    @get:Rule(order = 0)
    val clearUserDataRule = object : ExternalResource() {
        override fun before() = runBlocking {
            GlobalContext.get().get<ForoomUserDataStore>().clearUserData()
        }
    }

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val registrationSteps = RegistrationSteps()

    @Test
    fun loginWithValidUsernameAndInvalidPassword() {
        loginSteps
            .validateLoginScreenDisplayed()
            .enterUserName(Constants.VALID_USERNAME)
            .enterPassword(Constants.WRONG_PASSWORD)
            .clickLogIn()
            .validatePasswordErrorDisplayed()
            .validateUserNameErrorNotDisplayed()
    }

    @Test
    fun loginWithInvalidUsernameAndInvalidPassword() {
        loginSteps
            .validateLoginScreenDisplayed()
            .enterUserName(Constants.NON_EXISTING_USERNAME)
            .enterPassword(Constants.WRONG_PASSWORD)
            .clickLogIn()
            .validateUserNameErrorDisplayed()
            .validatePasswordErrorDisplayed()
    }

    @Test
    fun registrationWithUniqueUsername() {
        loginSteps
            .validateLoginScreenDisplayed()
            .clickSignUp()

        registrationSteps
            .validateRegistrationScreenDisplayed()
            .enterUserName(registrationSteps.generateUniqueUserName())
            .enterPassword(Constants.VALID_PASSWORD)
            .enterRepeatPassword(Constants.VALID_PASSWORD)
            .selectAvatar()
            .clickSignUp()
            .validateHomeScreenDisplayed()
    }
}