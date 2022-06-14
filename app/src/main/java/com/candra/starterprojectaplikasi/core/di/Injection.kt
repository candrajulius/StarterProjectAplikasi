package com.candra.starterprojectaplikasi.core.di

import android.content.Context
import com.candra.starterprojectaplikasi.core.data.TourismRepository
import com.candra.starterprojectaplikasi.core.data.source.local.LocalDataSource
import com.candra.starterprojectaplikasi.core.data.source.local.room.TourismDatabase
import com.candra.starterprojectaplikasi.core.data.source.remote.RemoteDataSource
import com.candra.starterprojectaplikasi.core.data.source.remote.network.ApiConfig
import com.candra.starterprojectaplikasi.core.domain.usercase.TourismInteractor
import com.candra.starterprojectaplikasi.core.domain.usercase.TourismUseCase
import com.candra.starterprojectaplikasi.core.utils.AppExecutors
import com.candra.starterprojectaplikasi.core.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): TourismRepository {
        val database = TourismDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.tourismDao())
        val appExecutors = AppExecutors()

        return TourismRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideTourismUseCase(context: Context): TourismUseCase{
        val repository = provideRepository(context)
        return TourismInteractor(repository)
    }
}