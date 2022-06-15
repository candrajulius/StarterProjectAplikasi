package com.candra.starterprojectaplikasi.core.di

import com.candra.starterprojectaplikasi.core.domain.usercase.TourismInteractor
import com.candra.starterprojectaplikasi.core.domain.usercase.TourismUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {
    @Binds
    @ViewModelScoped
    abstract fun provideTourismUseCase(tourismInteractor: TourismInteractor): TourismUseCase
}