package com.candra.starterprojectaplikasi.core.data.source.local.room

import androidx.room.*
import com.candra.starterprojectaplikasi.core.data.source.local.entity.TourismEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface TourismDao {

    @Query("SELECT * FROM tourism")
    fun getAllTourism(): Flowable<List<TourismEntity>>

    @Query("SELECT * FROM tourism where isFavorite = 1")
    fun getFavoriteTourism(): Flowable<List<TourismEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTourism(tourism: List<TourismEntity>): Completable

    @Update
    fun updateFavoriteTourism(tourism: TourismEntity)
}
// Kesimpulan
/*
Tipe yang digunakan yaitu Flowable untuk mengambil data dan Completable untuk memasukkan data. Mengapa Completable? Hal ini karena Anda tak memerlukan kembalian pada saat insert, cukup informasi apakah proses sudah selesai (complete) atau eror.
 */