package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.Constants.TIMEOUT
import com.example.foroom.pages.ChatsPage

class ChatSteps {
    private val chatsPage = ChatsPage()

    fun validateHomeScreenDisplayed() = apply {
        onView(chatsPage.navBar).waitUntilVisible(TIMEOUT).check(matches(isDisplayed()))
    }

    fun searchChat(chatName: String) = apply {
        onView(chatsPage.searchChatField).input(chatName)
    }

    fun validateChatCardDisplayed(chatName: String) = apply {
        onView(chatsPage.chatCard(chatName)).waitUntilVisible(TIMEOUT).check(matches(isDisplayed()))
    }

    fun openChat(chatName: String) = apply {
        onView(chatsPage.openChatButton(chatName)).tap()
    }
}