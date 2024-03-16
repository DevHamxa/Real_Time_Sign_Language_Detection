package com.example.rtsignlanguagedetection.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rtsignlanguagedetection.view.dashboard.Dashboard
import com.example.rtsignlanguagedetection.view.dashboard.PrivacyPolicy
import com.example.rtsignlanguagedetection.view.dashboard.TermsAndCondition
import com.example.rtsignlanguagedetection.view.dashboard.About
import com.example.rtsignlanguagedetection.view.dashboard.Dictionary
import com.example.rtsignlanguagedetection.view.welcome.OnBoardingScreenView
import com.example.rtsignlanguagedetection.view.welcome.SplashScreenView

@ExperimentalMaterialApi
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }

    NavHost(navController, startDestination = Screen.Splash.route) {

        //Splash Screen
        composable(Screen.Splash.route) {
            SplashScreenView(actions)
        }

        //OnBoarding Screen
        composable(Screen.OnBoarding.route) {
            OnBoardingScreenView(actions)
        }

        composable(Screen.OnPrivacy.route) {
            PrivacyPolicy(actions)
        }

        composable(Screen.OnTermss.route) {
            TermsAndCondition(actions)
        }

        //Dashboard
        composable(Screen.Dashboard.route) {
            Dashboard(actions)
        }

        //Vegetable Detail
        composable(Screen.About.route) {
            About(actions)
        }

        composable(Screen.Dictionary.route) {
            Dictionary(actions)
        }
    }
}

class MainActions(private val navController: NavHostController) {

    val popBackStack: () -> Unit = {
        navController.popBackStack()
    }

    val upPress: () -> Unit = {
        navController.navigateUp()
    }

    val gotoOnBoarding: () -> Unit = {
        navController.popBackStack()
        navController.navigate(Screen.OnBoarding.route)
    }
    val gotoOnTermss: () -> Unit = {
        navController.navigate(Screen.OnTermss.route)
    }

    val gotoOnPrivacy: () -> Unit = {
        navController.navigate(Screen.OnPrivacy.route)
    }

    val gotoOnDashboard: () -> Unit = {
        navController.popBackStack()
        navController.navigate(Screen.Dashboard.route)
    }

    val gotoAbout: () -> Unit = {
        navController.navigate(Screen.About.route)
    }
    val gotoDictionary: () -> Unit = {
        navController.navigate(Screen.Dictionary.route)
    }
}