package com.candra.core.domain.usercase

import com.candra.core.data.Resource
import com.candra.core.domain.model.Tourism
import com.candra.core.domain.repository.ITourismRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TourismInteractor @Inject constructor(
    private val tourismRepository: ITourismRepository
): TourismUseCase{
    override fun getAllTourism(): Flow<com.candra.core.data.Resource<List<Tourism>>> {
        return tourismRepository.getAllTourism()
    }

    override fun getFavoriteTourism(): Flow<List<Tourism>> {
        return tourismRepository.getFavoriteTourism()
    }

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) {
       return tourismRepository.setFavoriteTourism(tourism,state)
    }


}