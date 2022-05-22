package com.candra.starterprojectaplikasi.core.domain.usercase

import androidx.lifecycle.LiveData
import com.candra.starterprojectaplikasi.core.data.Resource
import com.candra.starterprojectaplikasi.core.domain.model.Tourism
import com.candra.starterprojectaplikasi.core.domain.repository.ITourismRepository

class TourismInteractor(
    private val tourismRepository: ITourismRepository
): TourismUseCase{
    override fun getAllTourism(): LiveData<Resource<List<Tourism>>> = tourismRepository.getAllTourism()

    override fun getFavoriteTourism(): LiveData<List<Tourism>> = tourismRepository.getFavoriteTourism()

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) = tourismRepository.setFavoriteTourism(tourism,state)


}