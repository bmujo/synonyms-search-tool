package com.test.synonyms

import com.test.synonyms.data.repository.SynonymRepositoryImpl
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SynonymRepositoryTest {

    private lateinit var repository: SynonymRepositoryImpl

    @Before
    fun setUp() {
        repository = SynonymRepositoryImpl()
    }

    @Test
    fun `add two new words creates synonym set`() {
        repository.add("fast", "quick")
        val result = repository.search("fast").map { it.value }
        assertTrue(result.contains("quick"))
        assertTrue(repository.search("quick").map { it.value }.contains("fast"))
    }

    @Test
    fun `add word to existing set`() {
        repository.add("fast", "quick")
        repository.add("fast", "speedy")
        val result = repository.search("quick").map { it.value }
        assertTrue(result.containsAll(listOf("fast", "speedy")))
    }

    @Test
    fun `merge two synonym sets`() {
        repository.add("a", "b") // Set A
        repository.add("c", "d") // Set B
        repository.add("b", "c") // Merge A and B

        val result = repository.search("a").map { it.value }
        assertTrue(result.containsAll(listOf("b", "c", "d")))
    }

    @Test
    fun `search for non-existing word returns empty list`() {
        val result = repository.search("ghost")
        assertTrue(result.isEmpty())
    }

    @Test
    fun `search result does not include queried word`() {
        repository.add("run", "sprint")
        val result = repository.search("run").map { it.value }
        assertFalse(result.contains("run"))
    }
}