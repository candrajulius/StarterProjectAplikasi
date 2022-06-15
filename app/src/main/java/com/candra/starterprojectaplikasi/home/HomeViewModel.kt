package com.candra.starterprojectaplikasi.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.candra.starterprojectaplikasi.core.data.TourismRepository
import com.candra.starterprojectaplikasi.core.domain.usercase.TourismUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(tourismUseCase: TourismUseCase) : ViewModel() {

    val tourism = tourismUseCase.getAllTourism().asLiveData()

}