package com.odogwudev.apollographql.domain.repository

import com.odogwudev.apollographql.data.common.RickAndMortyResult
import com.odogwudev.apollographql.domain.models.CharactersModel
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    suspend fun getCharacters(page: Int): Flow<RickAndMortyResult<CharactersModel>>
}