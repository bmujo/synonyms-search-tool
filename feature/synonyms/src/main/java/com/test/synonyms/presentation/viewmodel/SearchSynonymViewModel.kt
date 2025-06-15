package com.test.synonyms.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.test.model.Word
import com.test.synonyms.data.repository.SynonymRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SearchSynonymViewModel @Inject constructor(
    private val synonymRepository: SynonymRepository,
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _results = MutableStateFlow<List<Word>>(emptyList())
    val results: StateFlow<List<Word>> = _results

    private val _hasSearched = MutableStateFlow(false)
    val hasSearched: StateFlow<Boolean> = _hasSearched

    fun onQueryChange(newQuery: String) {
        _searchQuery.value = newQuery
        _results.value = emptyList()
        _hasSearched.value = false
    }

    fun onSearch() {
        val query = _searchQuery.value.trim().lowercase()

        if (query.isNotEmpty()) {
            _results.value = synonymRepository.search(query)
            _hasSearched.value = true
        } else {
            _results.value = emptyList()
            _hasSearched.value = false
        }
    }
}
