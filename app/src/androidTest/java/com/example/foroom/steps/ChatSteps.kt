package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.Constants.CHAT_NAME
import com.example.foroom.data.Constants.TIMEOUT
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage

class ChatSteps {
    private val chatsPage = ChatsPage()
    private val createChatPage = CreateChatPage()

    fun generateUniqueChatName() = "$CHAT_NAME ${System.currentTimeMillis()}"

    fun validateHomeScreenDisplayed() = apply {
        onView(chatsPage.navBar).waitUntilVisible(TIMEOUT).check(matches(isDisplayed()))
    }

    fun openCreateChat() = apply {
        onView(chatsPage.homeNavigationCreateChat).tap()
    }

    fun enterChatName(chatName: String) = apply {
        onView(createChatPage.chatNameField).input(chatName)
    }

    fun selectChatImage(position: Int = 1) = apply {
        onView(createChatPage.loadedChatImages).waitUntilVisible(TIMEOUT)
        createChatPage.chatImageItem.tap(byPosition = position)
    }

    fun clickCreateChat() = apply {
        onView(createChatPage.createChatButton).tap()
    }

    fun validateChatTitleDisplayed(chatName: String) = apply {
        onView(chatsPage.chatTitle(chatName)).waitUntilVisible(TIMEOUT).check(matches(isDisplayed()))
    }

    fun closeChat() = apply {
        onView(chatsPage.chatCloseButton).tap()
    }

    fun searchChat(chatName: String) = apply {
        onView(chatsPage.searchChatField).input(chatName)
    }

    fun validateChatCardDisplayed(chatName: String) = apply {
        onView(chatsPage.chatCard(chatName)).waitUntilVisible(TIMEOUT).check(matches(isDisplayed()))
    }
}