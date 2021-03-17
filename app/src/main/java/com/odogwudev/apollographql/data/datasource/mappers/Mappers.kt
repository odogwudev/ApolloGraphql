package com.odogwudev.apollographql.data.datasource.mappers

import com.odogwudev.apollographql.GetCharactersQuery
import com.odogwudev.apollographql.domain.models.CharactersModel
import com.odogwudev.apollographql.domain.models.EpisodeModel
import com.odogwudev.apollographql.domain.models.InfoModel
import com.odogwudev.apollographql.domain.models.SingleCharacterModel

fun GetCharactersQuery.Info.mapToDomainModel() = InfoModel(pages ?: 0, count ?: 0, next ?: 0)

fun GetCharactersQuery.Episode.mapToDomainModel() = EpisodeModel(id ?: "", name ?: "")

fun GetCharactersQuery.Result.mapToDomainModel() = SingleCharacterModel(
    id ?: "",
    name ?: "",
    image ?: "",
    episode?.map { it!!.mapToDomainModel() } ?: emptyList()
)

fun GetCharactersQuery.Characters.mapToDomainModel() = CharactersModel(
    info?.mapToDomainModel() ?: InfoModel(),
    results?.map { it!!.mapToDomainModel() } ?: emptyList()
)