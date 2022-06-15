package com.candra.starterprojectaplikasi.core.di

import com.candra.starterprojectaplikasi.core.data.TourismRepository
import com.candra.starterprojectaplikasi.core.domain.repository.ITourismRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(tourismRepository: TourismRepository): ITourismRepository
}
/*
Kesimpulan
Kita tahu bahwa ITourismRepository adalah interface, artinya ia membutuhkan concrete implementation, dalam hal ini yaitu TourismRepository. Oleh karena itulah kita perlu mendefinisikannya di sini, caranya yaitu dengan membuat abstract method dengan parameter berupa implementation class (TourismRepository) dan kembalian berupa interface (ITourismRepository).
Perhatikan juga karena fungsinya abstract maka class-nya juga harus abstract, karena itulah biasanya module untuk @Binds ini disendirikan. Jika Anda ingin tetap menggabungkannya dengan @Provides Anda harus membuat fungsi yang lain menjadi static dengan menambahkan annotation @JvmStatic pada Kotlin.

Namun mengapa kita menggunakan @Binds? Hal ini karena dari segi performa ia lebih efisien. Dengan menggunakan abstract, sistem tidak perlu membuat instance baru seperti yang dilakukan @Provides. Karena itulah Anda menggunakan @Binds pada RepositoryModule. Begitu juga pada AppModule yang digunakan untuk mendefinisikan TourismUseCase yang merupakan interface dari TourismInteractor.

 Untuk membuat Module di dalam Module, Anda perlu menambahkan parameter includes

 Dengan menggunakan includes ini Anda bisa menggunakan komponen yang ada pada NetworkModule dan DatabaseModule pada RepositoryModule. Selain itu, ia juga berfungsi supaya pembagian Module menjadi lebih rapi.
 */