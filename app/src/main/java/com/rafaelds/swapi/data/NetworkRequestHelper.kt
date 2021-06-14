package com.rafaelds.swapi.data

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@Singleton
class NetworkRequestHelper @Inject constructor(private val requestQueue: RequestQueue) {

    sealed class NetworkResponse<T> {
        class Success<T>(val data: T) : NetworkResponse<T>()
        class Error<T>(val message: String) : NetworkResponse<T>()
    }

    suspend fun <T> request(uri: String, serializer: KSerializer<T>): NetworkResponse<T> {
        return suspendCoroutine { continuation ->
            val request = JsonObjectRequest(
                Request.Method.GET, uri, null,
                { response ->
                    val data = Json {
                        ignoreUnknownKeys = true
                    }.decodeFromString(serializer, response.toString())
                    continuation.resume(NetworkResponse.Success(data))
                },
                {
                    continuation.resume(NetworkResponse.Error(it?.message ?: "Unknown error"))
                }
            )
            requestQueue.add(request)
        }

    }

}