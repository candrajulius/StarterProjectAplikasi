package com.candra.starterprojectaplikasi.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.candra.starterprojectaplikasi.core.data.source.local.LocalDataSource
import com.candra.starterprojectaplikasi.core.data.source.remote.RemoteDataSource
import com.candra.starterprojectaplikasi.core.data.source.remote.network.ApiResponse
import com.candra.starterprojectaplikasi.core.data.source.remote.response.TourismResponse
import com.candra.starterprojectaplikasi.core.domain.model.Tourism
import com.candra.starterprojectaplikasi.core.domain.repository.ITourismRepository
import com.candra.starterprojectaplikasi.core.utils.AppExecutors
import com.candra.starterprojectaplikasi.core.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TourismRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): ITourismRepository{

    companion object {
        @Volatile
        private var instance: TourismRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): TourismRepository =
            instance ?: synchronized(this) {
                instance ?: TourismRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllTourism(): Flowable<Resource<List<Tourism>>> =
        object : NetworkBoundResource<List<Tourism>, List<TourismResponse>>() {
            override fun loadFromDB(): Flowable<List<Tourism>> {
                return localDataSource.getAllTourism().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Tourism>?): Boolean {
                return true
            }

            override fun createCall(): Flowable<ApiResponse<List<TourismResponse>>> {
                return remoteDataSource.getAllTourism()
            }

            override fun saveCallResult(data: List<TourismResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertTourism(tourismList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun getFavoriteTourism(): Flowable<List<Tourism>> {
        return localDataSource.getFavoriteTourism().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
        appExecutors.diskIO().execute{localDataSource.setFavoriteTourism(tourismEntity,state)}
    }

    // Kesimpulan
    /*

     */


}