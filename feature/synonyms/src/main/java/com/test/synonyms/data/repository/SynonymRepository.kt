package com.test.synonyms.data.repository

import com.test.model.Word

interface SynonymRepository {
    fun add(wordA: String, wordB: String)
    fun search(word: String): List<Word>
    fun getAllWords(): List<Word>
}