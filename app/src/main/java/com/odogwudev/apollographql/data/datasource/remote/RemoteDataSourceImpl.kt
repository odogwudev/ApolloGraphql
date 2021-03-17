package com.odogwudev.apollographql.data.datasource.remote

import com.apollographql.apollo.coroutines.await
import com.odogwudev.apollographql.GetCharactersQuery
import com.odogwudev.apollographql.R
import com.odogwudev.apollographql.data.GraphQlApolloClient
import com.odogwudev.apollographql.data.common.DataSourceException
import com.odogwudev.apollographql.data.common.RickAndMortyResult

class RemoteDataSourceImpl : RemoteDataSource {

    override suspend fun getCharacters(page: Int): RickAndMortyResult<GetCharactersQuery.Characters?> {
        return try {
            val result = GraphQlApolloClient.getCharacters(page).await()
            if (result.hasErrors()) {
                RickAndMortyResult.Error(DataSourceException.Server(result.errors?.first()))
            } else {
                RickAndMortyResult.Success(result.data?.characters)
            }
        } catch (e: Exception) {
            RickAndMortyResult.Error(DataSourceException.Unexpected(R.string.error_unexpected_message))
        }
    }
}