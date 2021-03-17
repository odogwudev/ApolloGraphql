package com.odogwudev.apollographql.data.datasource.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.odogwudev.apollographql.domain.models.EpisodeModel
import com.odogwudev.apollographql.domain.models.SingleCharacterModel
import java.io.Serializable

class CharactersDataConverter : Serializable {

    private val gson by lazy { Gson() }

    @TypeConverter
    fun fromCharactersString(value: String): List<SingleCharacterModel> {
        val listType = object : TypeToken<List<SingleCharacterModel>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromListCharacters(list: List<SingleCharacterModel>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromEpisodesString(value: String): List<EpisodeModel> {
        val listType = object : TypeToken<List<EpisodeModel>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromListEpisodes(list: List<EpisodeModel>): String {
        return gson.toJson(list)
    }
}