package com.candra.starterprojectaplikasi.core.data.source.remote


import com.candra.starterprojectaplikasi.core.data.source.remote.network.ApiResponse
import com.candra.starterprojectaplikasi.core.data.source.remote.network.ApiService
import com.candra.starterprojectaplikasi.core.data.source.remote.response.TourismResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    fun getAllTourism(): Flow<ApiResponse<List<TourismResponse>>> {
        //get data from local json
        return flow {
            try {
                val response = apiService.getList().places
                response.let {
                    if (it.isNotEmpty()){
                        emit(ApiResponse.Success(it))
                    }else{
                        emit(ApiResponse.Empty)
                    }
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}
/*
Unutk membuat Flow, Disini kita menggunakan flow builder flow dan mengirimkan hasil response
Menggunakan fungsi emit. Kemudian kita juga menggunakan Dispatcher IO pada flowOn. Karena ini merupakan proses mengambil
data dari server
 */