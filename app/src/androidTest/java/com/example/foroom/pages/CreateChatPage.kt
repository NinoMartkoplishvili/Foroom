package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.R as DesignR
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.design_system.components.image_chooser.ImageChooserListView
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class CreateChatPage {
    val chatNameInput: Matcher<View> = withId(R.id.chatNameInput)
    val chatNameField: Matcher<View> = allOf(withId(DesignR.id.inputEditText), isDescendantOfA(chatNameInput))
    val chatImageChooser: Matcher<View> = withId(R.id.chatImageChooser)
    val chatImageItem: Matcher<View> = allOf(isAssignableFrom(ImageChooserItemView::class.java), isDescendantOfA(chatImageChooser))
    val loadedChatImages: Matcher<View> = allOf(chatImageChooser, imagesLoaded())
    val createChatButton: Matcher<View> = withId(R.id.createChatButton)

    private fun imagesLoaded() = object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("images are loaded")
        }

        override fun matchesSafely(view: View) =
            view is ImageChooserListView && view.isChoosingEnabled && view.images.isNotEmpty()
    }
}