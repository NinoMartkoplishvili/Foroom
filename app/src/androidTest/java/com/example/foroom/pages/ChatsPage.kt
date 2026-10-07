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
    val homeNavigationProfile: Matcher<View> = withId(R.id.homeNavigationProfile)

    val searchChatField: Matcher<View> = allOf(isAssignableFrom(EditText::class.java), isDescendantOfA(withId(R.id.searchChatInput)))

    fun chatCard(chatName: String): Matcher<View> = allOf(withId(DesignR.id.chatTitleTextView), withText(chatName), isDescendantOfA(withId(R.id.chatsRecyclerView)))

    fun openChatButton(chatName: String): Matcher<View> = allOf(withId(DesignR.id.sendMessageButton), hasSibling(chatCard(chatName)))
}