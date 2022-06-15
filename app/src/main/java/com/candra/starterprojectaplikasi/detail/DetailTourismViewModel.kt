package com.candra.starterprojectaplikasi.detail

import androidx.lifecycle.ViewModel
import com.candra.starterprojectaplikasi.core.data.TourismRepository
import com.candra.starterprojectaplikasi.core.data.source.local.entity.TourismEntity
import com.candra.starterprojectaplikasi.core.domain.model.Tourism
import com.candra.starterprojectaplikasi.core.domain.usercase.TourismUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailTourismViewModel @Inject constructor(private val tourismUseCase: TourismUseCase) : ViewModel() {
    fun setFavoriteTourism(tourism: Tourism, newStatus:Boolean) = tourismUseCase.setFavoriteTourism(tourism, newStatus)
}