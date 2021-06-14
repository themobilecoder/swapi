package com.rafaelds.swapi.data.network

import kotlinx.serialization.KSerializer

abstract class RemoteService <T, U> constructor(private val networkRequestHelper: NetworkRequestHelper) {

    abstract val outputSerializer: KSerializer<T>
    abstract fun dtoToModelConverter(dto: T): U

    suspend fun fetchData(url: String): T {
        val response = networkRequestHelper.request(
            url,
            outputSerializer
        )
        return when (response) {
            is NetworkRequestHelper.NetworkResponse.Success -> {
                response.data
            }
            else -> {
                throw Exception()
            }
        }
    }
}