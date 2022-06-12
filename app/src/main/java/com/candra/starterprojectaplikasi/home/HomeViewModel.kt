package com.candra.starterprojectaplikasi.home

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.candra.starterprojectaplikasi.core.data.TourismRepository
import com.candra.starterprojectaplikasi.core.domain.usercase.TourismUseCase

class HomeViewModel(tourismUseCase: TourismUseCase) : ViewModel() {

    val tourism = LiveDataReactiveStreams.fromPublisher(tourismUseCase.getAllTourism())

    // kesimpulan
    /*
     Mengubah data flowbale ke live data
     */
}