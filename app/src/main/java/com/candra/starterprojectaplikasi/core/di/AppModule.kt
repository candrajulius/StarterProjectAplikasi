package com.candra.starterprojectaplikasi.core.di

import com.candra.starterprojectaplikasi.core.domain.usercase.TourismInteractor
import com.candra.starterprojectaplikasi.core.domain.usercase.TourismUseCase
import com.candra.starterprojectaplikasi.detail.DetailTourismViewModel
import com.candra.starterprojectaplikasi.favorite.FavoriteViewModel
import com.candra.starterprojectaplikasi.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<TourismUseCase>{ TourismInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailTourismViewModel(get()) }
}