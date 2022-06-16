package com.candra.core.data

import com.candra.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*


abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result : Flow<com.candra.core.data.Resource<ResultType>> = flow {
        emit(com.candra.core.data.Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)){
            emit(com.candra.core.data.Resource.Loading())
            when(val apiResource = createCall().first()){
                is ApiResponse.Success -> {
                    saveCallResult(apiResource.data)
                    emitAll(loadFromDB().map { com.candra.core.data.Resource.Success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { com.candra.core.data.Resource.Success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(com.candra.core.data.Resource.Error<ResultType>(apiResource.errorMessage))
                }
            }
        }else{
            emitAll(loadFromDB().map { com.candra.core.data.Resource.Success(it) })
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<com.candra.core.data.Resource<ResultType>> = result
}