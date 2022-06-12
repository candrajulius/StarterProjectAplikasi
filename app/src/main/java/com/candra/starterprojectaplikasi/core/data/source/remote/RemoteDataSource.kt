package com.candra.starterprojectaplikasi.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.candra.starterprojectaplikasi.core.data.source.remote.network.ApiResponse
import com.candra.starterprojectaplikasi.core.data.source.remote.network.ApiService
import com.candra.starterprojectaplikasi.core.data.source.remote.response.ListTourisResponse
import com.candra.starterprojectaplikasi.core.data.source.remote.response.TourismResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    @SuppressLint("CheckResult")
    fun getAllTourism(): Flowable<ApiResponse<List<TourismResponse>>> {
        val resultData = PublishSubject.create<ApiResponse<List<TourismResponse>>>()

        //get data from remote api
        val client = apiService.getList()

        client.subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe(
                { response ->
                    val dataArray = response.places
                    resultData.onNext(
                        if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty
                    )
                }, { error ->
                    resultData.onNext(
                        ApiResponse.Error(error.message.toString())
                    )
                    Log.e("TAG", "getAllTourism: ${error.toString()}", )
                }
            )

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }


    // Kesimpulan
    /*
    take(1) untuk mengambil data dari API sekali saja.
    Kemudian karena kita akan menampung data di dalam ApiResponse, maka di sini kita menggunakan PublishSubject
    meng-emit setiap response menggunakan fungsi onNext
    Kemudian untuk mengubah Subject menjadi Flowable kita menggunakan fungsi toFlowable
    Backpressure strategy yang digunakan yaitu Buffer, hal ini karena kita ingin mengambil setiap data walaupun ter-delay.
     */

}