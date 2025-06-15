package com.test.synonyms_search_tool.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavBackStackEntry

private const val TIME_DURATION = 300

val enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearEasing)
    )
}

val exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutHorizontally(
        targetOffsetX = { -it },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearEasing)
    )
}

val popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInHorizontally(
        initialOffsetX = { -it },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearEasing)
    )
}

val popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearEasing)
    )
}