package com.example.foroom.steps

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.input
import com.example.foroom.Helper.swiper
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.Constants.MAX_SWIPES
import com.example.foroom.data.Constants.TIMEOUT
import com.example.foroom.pages.ConversationPage
import org.hamcrest.Matcher
import org.junit.Assert.assertFalse

class ConversationSteps {
    private val conversationPage = ConversationPage()

    fun generateUniqueMessage(text: String) = "$text ${System.currentTimeMillis()}"

    fun validateChatTitleDisplayed(chatName: String) = apply {
        onView(conversationPage.chatTitle(chatName)).waitUntilVisible(TIMEOUT).check(matches(isDisplayed()))
    }

    fun enterMessage(text: String) = apply {
        onView(conversationPage.messageField).input(text)
    }

    fun clickSend() = apply {
        onView(conversationPage.sendMessageButton).tap()
    }

    fun validateMessageDisplayed(text: String, sender: String) = apply {
        onView(conversationPage.message(text, sender)).waitUntilVisible(TIMEOUT).check(matches(isDisplayed()))
    }
    fun validateMessageNotDisplayed(text: String, sender: String) = apply {
        assertFalse(isMessageDisplayed(text, sender))
    }

    fun swipeToOlderMessage(text: String, sender: String) = apply {
        repeat(MAX_SWIPES) {
            if (isMessageDisplayed(text, sender)) return@apply
            swipeDownMessagesList()
        }
    }

    fun closeChat() = apply {
        onView(conversationPage.closeButton).tap()
    }

    private fun isMessageDisplayed(text: String, sender: String) = runCatching {
        onView(conversationPage.message(text, sender)).check(matches(isDisplayed()))
    }.isSuccess

    private fun swipeDownMessagesList() {
        val (top, bottom) = messagesListBounds()
        val height = bottom - top
        swiper(top + height / 4, bottom - height / 4, 300)
    }

    private fun messagesListBounds(): Pair<Int, Int> {
        var top = 0
        var bottom = 0
        onView(conversationPage.messagesRecyclerView).perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> = isDisplayed()

            override fun getDescription() = "get messages list bounds on screen"

            override fun perform(uiController: UiController, view: View) {
                val location = IntArray(2)
                view.getLocationOnScreen(location)
                top = location[1]
                bottom = location[1] + view.height
            }
        })
        return top to bottom
    }
}