package com.rafaelds.swapi.data.network

import kotlinx.serialization.KSerializer


abstract class RemoteService<Dto> constructor(private val networkRequestHelper: NetworkRequestHelper) {

    abstract val outputSerializer: KSerializer<Dto>

    suspend fun fetchData(url: String): Dto {
        val response = networkRequestHelper.request(
            url,
            outputSerializer
        )
        return when (response) {
            is NetworkRequestHelper.NetworkResponse.Success -> {
                response.data
            }
            else -> {
                throw RemoteServiceException()
            }
        }
    }

    class RemoteServiceException : Exception("Network Error")
}