package com.odogwudev.apollographql.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.odogwudev.apollographql.BuildConfig
import com.odogwudev.apollographql.domain.models.EpisodeModel
import com.odogwudev.apollographql.domain.models.SingleCharacterModel

@Database(entities = [SingleCharacterModel::class, EpisodeModel::class], version = 1)
@TypeConverters(CharactersDataConverter::class)
abstract class AppDb : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDb? = null

        fun getDatabase(context: Context): AppDb {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java,
                    BuildConfig.APPLICATION_ID
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}