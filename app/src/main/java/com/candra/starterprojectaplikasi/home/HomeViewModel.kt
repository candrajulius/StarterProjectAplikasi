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

/*
Kesimpulan
Karena Hilt sudah terintegrasi dengan Jetpack, Anda dapat melakukan Injection pada ViewModel dengan jauh lebih mudah tanpa perlu menggunakan multi-binding lagi. Yaitu dengan menggunakan anotasi @HiltViewModel dan @Inject
 */