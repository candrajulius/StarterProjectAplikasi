package com.candra.starterprojectaplikasi.core.domain.usercase

import androidx.lifecycle.LiveData
import com.candra.starterprojectaplikasi.core.data.Resource
import com.candra.starterprojectaplikasi.core.domain.model.Tourism
import kotlinx.coroutines.flow.Flow

interface TourismUseCase {

    fun getAllTourism(): Flow<Resource<List<Tourism>>>

    fun getFavoriteTourism(): Flow<List<Tourism>>

    fun setFavoriteTourism(tourism: Tourism,state: Boolean)



}