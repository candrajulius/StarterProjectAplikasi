package com.candra.starterprojectaplikasi.core.data.source.remote.network

import com.candra.starterprojectaplikasi.core.data.source.remote.response.ListTourisResponse
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    fun getList(): Flowable<ListTourisResponse>
}