package com.ayurvedahome.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.ayurvedahome.ads.AdManager
import com.ayurvedahome.analytics.AnalyticsManager
import com.ayurvedahome.domain.model.UserPreferences
import com.ayurvedahome.presentation.ui.screens.consent.ConsentScreen
import com.ayurvedahome.presentation.ui.screens.onboarding.OnboardingScreen
import com.ayurvedahome.presentation.ui.screens.utility.AyurvedaCategoryScreen
import com.ayurvedahome.presentation.ui.screens.utility.AyurvedaHomeScreen
import com.ayurvedahome.presentation.ui.screens.utility.AyurvedaRemedyDetailScreen

sealed class Screen(val route: String) {
    data object Consent : Screen("consent")
    data object Onboarding : Screen("onboarding")
    data object Home : Screen("home")
    data object Category : Screen("category/{id}") { fun create(id: String) = "category/$id" }
    data object Remedy : Screen("remedy/{id}") { fun create(id: String) = "remedy/$id" }
}

@Composable
fun AyurvedaHomeNavHost(
    navController: NavHostController,
    adManager: AdManager,
    analyticsManager: AnalyticsManager,
    preferences: UserPreferences,
    startDestination: String
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    DisposableEffect(navBackStackEntry?.destination?.route) {
        navBackStackEntry?.destination?.route?.let { analyticsManager.logScreenView(it) }
        onDispose { }
    }
    NavHost(navController, startDestination) {
        composable(Screen.Consent.route) {
            ConsentScreen(onComplete = {
                navController.navigate(Screen.Onboarding.route) { popUpTo(Screen.Consent.route) { inclusive = true } }
            })
        }
        composable(Screen.Onboarding.route) {
            OnboardingScreen(onComplete = {
                navController.navigate(Screen.Home.route) { popUpTo(Screen.Onboarding.route) { inclusive = true } }
            })
        }
        composable(Screen.Home.route) {
            AyurvedaHomeScreen(
                onCategory = { navController.navigate(Screen.Category.create(it)) },
                onRemedy = { navController.navigate(Screen.Remedy.create(it)) },
                adManager = adManager,
                adsEnabled = preferences.adsEnabled
            )
        }
        composable(Screen.Category.route, arguments = listOf(navArgument("id") { type = NavType.StringType })) {
            val id = it.arguments?.getString("id") ?: return@composable
            AyurvedaCategoryScreen(
                categoryId = id,
                onRemedy = { navController.navigate(Screen.Remedy.create(it)) },
                onBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Remedy.route, arguments = listOf(navArgument("id") { type = NavType.StringType })) {
            val id = it.arguments?.getString("id") ?: return@composable
            AyurvedaRemedyDetailScreen(id, onBack = { navController.popBackStack() })
        }
    }
}
