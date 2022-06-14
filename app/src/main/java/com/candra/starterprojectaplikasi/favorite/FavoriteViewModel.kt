package com.candra.starterprojectaplikasi.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.candra.starterprojectaplikasi.core.domain.usercase.TourismUseCase

class FavoriteViewModel(tourismUseCase: TourismUseCase) : ViewModel() {

    val favoriteTourism = tourismUseCase.getFavoriteTourism().asLiveData()

}