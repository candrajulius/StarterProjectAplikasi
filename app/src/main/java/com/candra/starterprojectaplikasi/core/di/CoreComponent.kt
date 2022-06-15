package com.candra.starterprojectaplikasi.core.di

import android.content.Context
import com.candra.starterprojectaplikasi.core.domain.repository.ITourismRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface CoreComponent{

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideRepository(): ITourismRepository
}
/*
Hal lain yang perlu diperhatikan adalah scope. Ada dua aturan yang harus dipenuhi yaitu :

Component tanpa scope tidak boleh bergantung (depend) pada component yang memiliki scope.

Component dengan scope tidak boleh bergantung (depend) pada component dengan scope yang sama.

 Intinya untuk hubungan antara dua Component harus memiliki Scope yang berbeda. Apabila kedua component memiliki scope yang sama, misalnya sama-sama @Singleton, maka akan muncul eror This @Singleton component cannot depend on scoped components.

Bagaimana jika pada AppComponent tidak menggunakan scope? Anda boleh mencobanya, nanti akan keluar eror (unscoped) cannot depend on scoped components. Lalu bagaimana jika kedua Component menggunakan @AppScope? Sistem akan menampilkan eror depends on scoped components in a non-hierarchical scope ordering.
 */