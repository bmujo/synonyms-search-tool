package com.test.synonyms.data.repository

import com.test.model.Word

class SynonymRepositoryImpl : SynonymRepository {
    private val groups = mutableListOf<MutableSet<String>>()
    private val map = mutableMapOf<String, MutableSet<String>>()

    init {
        preloadData()
    }

    private fun preloadData() {
        val sampleSynonyms = listOf(
            "happy" to listOf("joyful", "cheerful", "content"),
            "sad" to listOf("unhappy", "sorrowful", "downcast"),
            "fast" to listOf("quick", "rapid", "speedy"),
            "smart" to listOf("intelligent", "clever", "bright"),
            "angry" to listOf("mad", "furious", "irate"),
            "cold" to listOf("chilly", "freezing", "frosty"),
            "hot" to listOf("warm", "boiling", "scorching"),
            "big" to listOf("large", "huge", "gigantic"),
            "small" to listOf("tiny", "miniature", "little"),
            "easy" to listOf("simple", "effortless", "straightforward"),
            "hard" to listOf("difficult", "challenging", "tough"),
            "strong" to listOf("powerful", "sturdy", "robust"),
            "weak" to listOf("frail", "fragile", "delicate"),
            "bright" to listOf("luminous", "radiant", "vivid"),
            "dark" to listOf("dim", "gloomy", "shadowy"),
            "clean" to listOf("tidy", "neat", "spotless"),
            "dirty" to listOf("filthy", "grimy", "unclean")
        )

        for ((word, synonyms) in sampleSynonyms) {
            synonyms.forEach { synonym ->
                add(word, synonym)
            }
        }
    }

    override fun add(wordA: String, wordB: String) {
        val setA = map[wordA]
        val setB = map[wordB]

        when {
            setA == null && setB == null -> {
                val newSet = mutableSetOf(wordA, wordB)
                groups.add(newSet)
                map[wordA] = newSet
                map[wordB] = newSet
            }
            setA != null && setB == null -> {
                setA.add(wordB)
                map[wordB] = setA
            }
            setA == null && setB != null -> {
                setB.add(wordA)
                map[wordA] = setB
            }
            // Ensures transitive synonym logic
            setA != null && setB != null && setA !== setB -> {
                setA.addAll(setB)
                for (word in setB) {
                    map[word] = setA
                }
                groups.remove(setB)
            }
        }
    }

    override fun search(word: String): List<Word> {
        return map[word]?.filterNot { it == word }?.distinct()?.map { Word(it) } ?: emptyList()
    }

    override fun getAllWords(): List<Word> {
        return map.keys.distinct().map { Word(it) }
    }
}