package com.candra.starterprojectaplikasi.core.di

import androidx.room.Room
import com.candra.starterprojectaplikasi.core.data.TourismRepository
import com.candra.starterprojectaplikasi.core.data.source.local.LocalDataSource
import com.candra.starterprojectaplikasi.core.data.source.local.room.TourismDatabase
import com.candra.starterprojectaplikasi.core.data.source.remote.RemoteDataSource
import com.candra.starterprojectaplikasi.core.data.source.remote.network.ApiService
import com.candra.starterprojectaplikasi.core.domain.repository.ITourismRepository
import com.candra.starterprojectaplikasi.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory {
        get<TourismDatabase>().tourismDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            TourismDatabase::class.java,"Tourism.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120,TimeUnit.SECONDS)
            .readTimeout(120,TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://tourism-api.dicoding.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ITourismRepository> { TourismRepository(get(),get(),get()) }
}
/*
Kesimpulan
CoreModule
Secara keseluruhan penjelasan kode sudah kita bahas sebelumnya, yaitu kita menggunakan factory untuk object yang biasa dan single untuk object yang singleton. Kemudian untuk mendapatkan constructor kita menggunakan get().

Yang berbeda di sini yaitu bagaimana jika Anda ingin mem-provide interface dengan implementasinya? Anda cukup menuliskan secara eksplisit nilai yang ingin di-provide di dalam single<> seperti pada CoreModule in
 */