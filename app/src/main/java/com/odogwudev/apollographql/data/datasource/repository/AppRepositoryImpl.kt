package com.odogwudev.apollographql.data.datasource.repository

import com.odogwudev.apollographql.data.common.RickAndMortyResult
import com.odogwudev.apollographql.data.datasource.local.AppDao
import com.odogwudev.apollographql.data.datasource.remote.RemoteDataSource
import com.odogwudev.apollographql.domain.models.CharactersModel
import com.odogwudev.apollographql.data.datasource.mappers.mapToDomainModel
import com.odogwudev.apollographql.domain.models.InfoModel
import com.odogwudev.apollographql.domain.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart

class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val appDao: AppDao
) :
    AppRepository {
    override suspend fun getCharacters(page: Int): Flow<RickAndMortyResult<CharactersModel>> =
        flow {
            when (val result = remoteDataSource.getCharacters(page)) {
                is RickAndMortyResult.Success -> {
                    result.data?.let {
                        it.mapToDomainModel().apply {
                            appDao.saveListCharacters(results)
                            emit(RickAndMortyResult.Success(this))
                        }
                    }
                }
                is RickAndMortyResult.Error -> {
                    val listCharacters = appDao.getListCharacters()
                    if (listCharacters.isNotEmpty()) {
                        emit(
                            RickAndMortyResult.Success(
                                CharactersModel(
                                    InfoModel(),
                                    listCharacters
                                )
                            )
                        )
                    } else {
                        emit(RickAndMortyResult.Error(result.exception))
                    }
                }
            }
        }.onStart { emit(RickAndMortyResult.Loading) }
}