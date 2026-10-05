package com.example.foroom.pages

import android.view.View
import android.widget.EditText
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.design_system.R as DesignR
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class ChatsPage {
    val navBar: Matcher<View> = withId(R.id.navBar)
    val homeNavigationCreateChat: Matcher<View> = withId(R.id.homeNavigationCreateChat)
    val homeNavigationProfile: Matcher<View> = withId(R.id.homeNavigationProfile)

    val searchChatField: Matcher<View> = allOf(isAssignableFrom(EditText::class.java), isDescendantOfA(withId(R.id.searchChatInput)))

    private val messagesRecyclerView: Matcher<View> = withId(R.id.messagesRecyclerView)
    val chatCloseButton: Matcher<View> = allOf(withId(R.id.closeButton), hasSibling(messagesRecyclerView))
    val chatHeader: Matcher<View> = allOf(withId(R.id.chatHeaderView), hasSibling(messagesRecyclerView))

    fun chatTitle(chatName: String): Matcher<View> = allOf(withText(chatName), isDescendantOfA(chatHeader))

    fun chatCard(chatName: String): Matcher<View> =
        allOf(withId(DesignR.id.chatTitleTextView), withText(chatName), isDescendantOfA(withId(R.id.chatsRecyclerView)))
}