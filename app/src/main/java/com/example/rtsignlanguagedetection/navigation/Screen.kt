package com.example.rtsignlanguagedetection.navigation;

import androidx.annotation.StringRes
import com.example.rtsignlanguagedetection.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Splash : Screen("splash", R.string.splash)
    object OnBoarding : Screen("onboarding", R.string.onboarding)
    object OnPrivacy : Screen("onprivacy", R.string.onprivacy)
    object OnTermss : Screen("ontermss", R.string.ontermss)
    object Dashboard : Screen("dashboard", R.string.dashboard)
    object About : Screen("about", R.string.about)
    object Dictionary : Screen("dictionary", R.string.dictionary)


}