package com.test.synonyms.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.test.model.Word
import com.test.synonyms.presentation.viewmodel.SearchSynonymViewModel
import com.test.ui.components.buttons.CustomButton
import com.test.ui.components.cards.SynonymCard
import com.test.ui.components.headers.SynonymHeaderBar
import com.test.ui.components.inputs.CustomInput
import com.test.ui.preview.SynonymsSearchToolPreview

@Composable
fun SearchSynonymScreen(
    viewModel: SearchSynonymViewModel = hiltViewModel(),
    onNavigateToAdd: () -> Unit
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val results by viewModel.results.collectAsState()
    val hasSearched by viewModel.hasSearched.collectAsState()

    SearchSynonymContent(
        query = searchQuery,
        results = results,
        hasSearched = hasSearched,
        onQueryChange = viewModel::onQueryChange,
        onSearchClick = viewModel::onSearch,
        onAddClick = onNavigateToAdd
    )
}

@Composable
private fun SearchSynonymContent(
    query: String,
    results: List<Word>,
    hasSearched: Boolean,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onAddClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SynonymHeaderBar()

        Column(modifier = Modifier.padding(16.dp)) {
            CustomInput(
                value = query,
                onValueChange = onQueryChange,
                placeholder = "Search word",
                showSearchIcon = true,
                onSearchClick = onSearchClick
            )

            Spacer(modifier = Modifier.height(16.dp))

            when {
                hasSearched && results.isEmpty() -> {
                    Text("No word found.", style = MaterialTheme.typography.bodyLarge)
                }

                hasSearched && results.isNotEmpty() -> {
                    SynonymCard(
                        word = query,
                        synonyms = results
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            CustomButton(
                text = "Add New Word",
                onClick = onAddClick
            )
        }
    }
}

@SynonymsSearchToolPreview
@Composable
fun SearchSynonymContentPreview() {
    SynonymsSearchToolPreview {
        SearchSynonymContent(
            query = "clean",
            results = listOf<Word>(Word("wash"), Word("rinse")),
            onQueryChange = {},
            onSearchClick = {},
            onAddClick = {},
            hasSearched = false
        )
    }
}