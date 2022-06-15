package com.candra.starterprojectaplikasi.core.di

import com.candra.starterprojectaplikasi.detail.DetailTourismActivity
import com.candra.starterprojectaplikasi.favorite.FavoriteFragment
import com.candra.starterprojectaplikasi.home.HomeFragment
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent{
    @Component.Factory
    interface Factory{
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: HomeFragment)
    fun inject(fragment: FavoriteFragment)
    fun inject(activity: DetailTourismActivity)
}
/*
Kesimpulan
Untuk menghubungkan antar CoreComponent dan AppComponent Anda menggunakan parameter dependencies pada Component.

Artinya AppComponent membutuhkan CoreComponent untuk bisa berjalan. Jika tidak menambahkan parameter, maka akan muncul eror @Component.Factory method has parameters for modules or components that aren't required. Artinya kita sudah menyediakan parameter CoreComponent namun tidak terpakai.

 Selain itu Anda juga perlu menambahkan CoreComponent di dalam parameter create. Jika tidak, maka akan muncul eror @Component.Factory method is missing parameters for required modules or components

 Karena AppComponent membutuhkan CoreComponent, maka Anda perlu membuat CoreComponent terlebih dahulu dan selanjutnya memasukkannya sebagai parameter ketika create AppComponent
 */