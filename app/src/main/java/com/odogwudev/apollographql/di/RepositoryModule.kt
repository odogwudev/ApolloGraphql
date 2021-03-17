package com.odogwudev.apollographql.di

import com.odogwudev.apollographql.data.datasource.local.AppDao
import com.odogwudev.apollographql.data.datasource.remote.RemoteDataSourceImpl
import com.odogwudev.apollographql.data.datasource.repository.AppRepositoryImpl
import com.odogwudev.apollographql.domain.repository.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAppRepository(appDao: AppDao): AppRepository {
        val remoteDataSourceImpl = RemoteDataSourceImpl()
        return AppRepositoryImpl(remoteDataSourceImpl, appDao)
    }
}