package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matcher

class ChangeLanguagePage {
    val georgianButton: Matcher<View> = withId(R.id.languageButtonGeo)
    val englishButton: Matcher<View> = withId(R.id.languageButtonEng)
}