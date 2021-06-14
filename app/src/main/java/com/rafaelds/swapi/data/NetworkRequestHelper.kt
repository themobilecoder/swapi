package com.rafaelds.swapi.data

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object NetworkRequestHelper{

    sealed class NetworkResponse<T> {
        class Success<T>(val data: T) : NetworkResponse<T>()
        class Failure<T>(val message: String) : NetworkResponse<T>()
    }

    suspend inline fun <reified T> request(uri: String, requestQueue: RequestQueue): NetworkResponse<T> {
        return suspendCoroutine { continuation ->
            val request = JsonObjectRequest(
                Request.Method.GET, uri, null,
                { response ->
                    val data = Json {
                        ignoreUnknownKeys = true
                    }.decodeFromString<T>(response.toString())
                    continuation.resume(NetworkResponse.Success(data))
                },
                {
                    continuation.resume(NetworkResponse.Failure(it?.message ?: "Unknown error"))
                }
            )
            requestQueue.add(request)
        }

    }

}