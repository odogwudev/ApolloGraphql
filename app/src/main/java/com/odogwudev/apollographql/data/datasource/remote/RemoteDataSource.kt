package com.odogwudev.apollographql.data.datasource.remote

import com.odogwudev.apollographql.GetCharactersQuery
import com.odogwudev.apollographql.data.common.RickAndMortyResult


interface RemoteDataSource {
    suspend fun getCharacters(page: Int): RickAndMortyResult<GetCharactersQuery.Characters?>
}