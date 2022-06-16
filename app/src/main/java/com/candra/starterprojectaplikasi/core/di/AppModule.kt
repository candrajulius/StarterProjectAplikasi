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

// Kesimpulan
/*
 Hilt adalah kebalikannya, Module yang menentukan masuk ke Component mana dengan menggunakan @InstallIn

 Hal ini karena Anda menggunakan Module di level ViewModel saja, sehingga yang digunakan adalah ViewModelComponent yang sudah disediakan Hilt. Hal lain yang sudah disediakan Hilt yaitu scope. Jika sebelumnya Anda membuat AppScope secara manual, maka saat ini Anda bisa menggunakan scope yang sudah disediakan, yaitu ViewModelScoped yang sesuai dengan Component-nya.
 */