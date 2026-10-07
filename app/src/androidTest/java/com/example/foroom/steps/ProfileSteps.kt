package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import com.example.foroom.Helper.tap
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.ProfilePage

class ProfileSteps {
    private val chatsPage = ChatsPage()
    private val profilePage = ProfilePage()

    fun openProfile() = apply {
        onView(chatsPage.homeNavigationProfile).tap()
    }

    fun clickSignOut() = apply {
        onView(profilePage.signOutItem).tap()
    }
}