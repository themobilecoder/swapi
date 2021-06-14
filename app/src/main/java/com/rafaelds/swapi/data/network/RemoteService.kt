package com.rafaelds.swapi.data.network

import com.rafaelds.swapi.data.DataState
import kotlinx.serialization.KSerializer

abstract class RemoteService <T, U> constructor(private val networkRequestHelper: NetworkRequestHelper) {

    abstract val url : String
    abstract val outputSerializer: KSerializer<T>
    abstract fun dtoToModelConverter(dto: T) : U

    suspend fun fetchData() : DataState<U> {
        val response = networkRequestHelper.request(
            url,
            outputSerializer
        )
        return when (response) {
            is NetworkRequestHelper.NetworkResponse.Success -> {
                val convertedModel = dtoToModelConverter(response.data)
                DataState.success(convertedModel)
            }
            else -> {
                DataState.error()
            }
        }
    }
}