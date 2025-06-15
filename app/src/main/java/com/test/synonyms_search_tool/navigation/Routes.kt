package com.test.synonyms_search_tool.navigation

sealed class Routes(val route: String) {
    data object Search : Routes("search")
    data object Add : Routes("add")
}

