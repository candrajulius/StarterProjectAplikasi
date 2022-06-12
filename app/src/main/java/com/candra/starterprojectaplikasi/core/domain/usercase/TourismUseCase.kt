package com.candra.starterprojectaplikasi.core.domain.usercase

import androidx.lifecycle.LiveData
import com.candra.starterprojectaplikasi.core.data.Resource
import com.candra.starterprojectaplikasi.core.domain.model.Tourism
import io.reactivex.Flowable

interface TourismUseCase {

    fun getAllTourism(): Flowable<Resource<List<Tourism>>>

    fun getFavoriteTourism(): Flowable<List<Tourism>>

    fun setFavoriteTourism(tourism: Tourism,state: Boolean)

}