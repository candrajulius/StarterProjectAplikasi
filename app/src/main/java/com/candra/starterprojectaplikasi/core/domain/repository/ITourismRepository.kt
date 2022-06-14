package com.candra.starterprojectaplikasi.core.domain.repository

import com.candra.starterprojectaplikasi.core.data.Resource
import com.candra.starterprojectaplikasi.core.domain.model.Tourism
import kotlinx.coroutines.flow.Flow

interface ITourismRepository {

    fun getAllTourism(): Flow<Resource<List<Tourism>>>

    fun getFavoriteTourism(): Flow<List<Tourism>>

    fun setFavoriteTourism(tourism: Tourism,state: Boolean)

}