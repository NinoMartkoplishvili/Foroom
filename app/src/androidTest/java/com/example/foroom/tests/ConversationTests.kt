package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.data.Constants
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.ConversationSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

@RunWith(AndroidJUnit4::class)
class ConversationTests {

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
    private val conversationSteps = ConversationSteps()

    @Test
    fun sentMessageInJohnWeekRemainsAfterReopeningChat() {
        val message = conversationSteps.generateUniqueMessage(Constants.DRINK_MESSAGE)

        logIn(Constants.USER_A)
        openChat(Constants.JOHN_WEEK_CHAT)
        sendMessageAndVerify(message, Constants.USER_A)

        conversationSteps.closeChat()
        openChat(Constants.JOHN_WEEK_CHAT)
        conversationSteps.validateMessageDisplayed(message, Constants.USER_A)
    }

    @Test
    fun questionIsDisplayedInOwnChat() {
        val question = conversationSteps.generateUniqueMessage(Constants.QUESTION_MESSAGE)

        logIn(Constants.USER_A)
        openChat(Constants.MY_CHAT)
        sendMessageAndVerify(question, Constants.USER_A)
    }

    @Test
    fun userBReadsGreetingFromUserAAndReplies() {
        val greeting = conversationSteps.generateUniqueMessage(Constants.GREETING_MESSAGE)
        val reply = conversationSteps.generateUniqueMessage(Constants.REPLY_MESSAGE)

        logIn(Constants.USER_A)
        openChat(Constants.SHARED_CHAT)
        sendMessageAndVerify(greeting, Constants.USER_A)
        repeat(Constants.ADDITIONAL_MESSAGES_COUNT) { sendMessage("${Constants.ADDITIONAL_MESSAGE} ${it + 1}") }
        conversationSteps.validateMessageNotDisplayed(greeting, Constants.USER_A)

        switchUser(Constants.USER_B)
        openChat(Constants.SHARED_CHAT)
        conversationSteps
            .swipeToOlderMessage(greeting, Constants.USER_A)
            .validateMessageDisplayed(greeting, Constants.USER_A)
        sendMessageAndVerify(reply, Constants.USER_B)

        switchUser(Constants.USER_A)
        openChat(Constants.SHARED_CHAT)
        conversationSteps.validateMessageDisplayed(reply, Constants.USER_B)
    }

    private fun logIn(userName: String) {
        loginSteps
            .validateLoginScreenDisplayed()
            .enterUserName(userName)
            .enterPassword(Constants.PASSWORD)
            .clickLogIn()

        chatSteps.validateHomeScreenDisplayed()
    }

    private fun openChat(chatName: String) {
        chatSteps
            .searchChat(chatName)
            .validateChatCardDisplayed(chatName)
            .openChat(chatName)

        conversationSteps.validateChatTitleDisplayed(chatName)
    }

    private fun sendMessage(text: String) {
        conversationSteps
            .enterMessage(text)
            .clickSend()
    }

    private fun sendMessageAndVerify(text: String, sender: String) {
        sendMessage(text)
        conversationSteps.validateMessageDisplayed(text, sender)
    }

    private fun switchUser(userName: String) {
        conversationSteps.closeChat()
        profileSteps
            .openProfile()
            .clickSignOut()
        logIn(userName)
    }
}