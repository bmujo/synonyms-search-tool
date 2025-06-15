package com.test.synonyms.di

import com.test.synonyms.data.repository.SynonymRepository
import com.test.synonyms.data.repository.SynonymRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SynonymsModule {

    @Provides
    @Singleton
    fun provideSynonymRepository(): SynonymRepository {
        return SynonymRepositoryImpl()
    }
}