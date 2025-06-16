package com.test.synonyms.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.test.model.Word
import com.test.synonyms.data.repository.SynonymRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AddSynonymViewModel @Inject constructor(
    private val synonymRepository: SynonymRepository,
) : ViewModel() {

    private val _word = MutableStateFlow("")
    val word: StateFlow<String> = _word

    private val _allWords = MutableStateFlow<List<Word>>(emptyList())
    val allWords: StateFlow<List<Word>> = _allWords

    private val _sortedWords = MutableStateFlow<List<Word>>(emptyList())
    val sortedWords: StateFlow<List<Word>> = _sortedWords

    private val _selectedWords = MutableStateFlow<Set<Word>>(emptySet())
    val selectedWords: StateFlow<Set<Word>> = _selectedWords

    private var firstEnter = false

    fun onWordChange(newWord: String) {
        _word.value = newWord
        if (firstEnter) {
            refreshSortedWords()
        } else {
            refreshAllWords()
            firstEnter = true
        }
    }

    fun toggleSynonym(word: Word) {
        _selectedWords.value = _selectedWords.value.toMutableSet().apply {
            if (contains(word)) remove(word) else add(word)
        }
        refreshSortedWords()
    }

    fun onConfirmAddSynonym(newSynonym: String) {
        val newSynonym = newSynonym.trim().lowercase()
        if (newSynonym.isNotEmpty()) {
            val newWord = Word(newSynonym)
            _selectedWords.value = _selectedWords.value + newWord
            if (_allWords.value.none { it.value == newWord.value }) {
                _allWords.value = _allWords.value + newWord
            }
            refreshSortedWords()
        }
    }

    fun addSynonyms() {
        val baseWord = word.value.trim().lowercase()
        selectedWords.value.forEach { synonym ->
            synonymRepository.add(baseWord, synonym.value)
        }
    }

    private fun refreshAllWords() {
        val all = synonymRepository.getAllWords()
        _allWords.value = all
        _sortedWords.value =
            all.sortedWith(compareByDescending<Word> { _selectedWords.value.contains(it) }.thenBy { it.value })
    }

    private fun refreshSortedWords() {
        _sortedWords.value =
            _allWords.value.sortedWith(compareByDescending<Word> { _selectedWords.value.contains(it) }.thenBy { it.value })
    }
}
