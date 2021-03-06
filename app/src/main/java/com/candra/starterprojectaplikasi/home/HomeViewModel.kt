package com.candra.starterprojectaplikasi.home

import androidx.lifecycle.ViewModel
import com.candra.starterprojectaplikasi.core.data.TourismRepository
import com.candra.starterprojectaplikasi.core.domain.usercase.TourismUseCase

class HomeViewModel(tourismUseCase: TourismUseCase) : ViewModel() {

    val tourism = tourismUseCase.getAllTourism()


}