package com.test.synonyms_search_tool.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.synonyms.presentation.ui.AddSynonymScreen
import com.test.synonyms.presentation.ui.SearchSynonymScreen

@Composable
fun RootNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Routes.Search.route,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition
    ) {
        composable(Routes.Search.route) {
            SearchSynonymScreen(
                onNavigateToAdd = {
                    navController.navigate(Routes.Add.route)
                }
            )
        }
        composable(Routes.Add.route) {
            AddSynonymScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}