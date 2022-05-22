package com.candra.starterprojectaplikasi.core.domain.usercase

import androidx.lifecycle.LiveData
import com.candra.starterprojectaplikasi.core.data.Resource
import com.candra.starterprojectaplikasi.core.domain.model.Tourism

interface TourismUseCase {

    fun getAllTourism(): LiveData<Resource<List<Tourism>>>

    fun getFavoriteTourism(): LiveData<List<Tourism>>

    fun setFavoriteTourism(tourism: Tourism,state: Boolean)

}