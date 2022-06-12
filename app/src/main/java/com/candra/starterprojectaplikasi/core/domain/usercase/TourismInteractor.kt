package com.candra.starterprojectaplikasi.core.domain.usercase

import androidx.lifecycle.LiveData
import com.candra.starterprojectaplikasi.core.data.Resource
import com.candra.starterprojectaplikasi.core.domain.model.Tourism
import com.candra.starterprojectaplikasi.core.domain.repository.ITourismRepository
import io.reactivex.Flowable

class TourismInteractor(
    private val tourismRepository: ITourismRepository
): TourismUseCase{
    override fun getAllTourism(): Flowable<Resource<List<Tourism>>> = tourismRepository.getAllTourism()

    override fun getFavoriteTourism(): Flowable<List<Tourism>> = tourismRepository.getFavoriteTourism()

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) = tourismRepository.setFavoriteTourism(tourism,state)
}