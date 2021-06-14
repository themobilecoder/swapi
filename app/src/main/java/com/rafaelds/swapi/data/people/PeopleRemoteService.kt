package com.rafaelds.swapi.data.people

import com.rafaelds.swapi.data.DataState
import com.rafaelds.swapi.data.NetworkConfig
import com.rafaelds.swapi.data.NetworkRequestHelper
import com.rafaelds.swapi.ui.people.People
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRemoteService @Inject constructor(
    private val networkConfig: NetworkConfig,
    private val networkRequestHelper: NetworkRequestHelper
) {

    companion object {
        private const val PEOPLE_SUB_URI = "people/"
        private val regexPattern = """(?:people/)(\d+)""".toRegex()
    }

    suspend fun getPeopleList(): DataState<List<People>> {
        val response = networkRequestHelper.request(
            "${networkConfig.baseUri}$PEOPLE_SUB_URI",
            PeopleListDTO.serializer()
        )
        return when (response) {
            is NetworkRequestHelper.NetworkResponse.Success -> {
                DataState.success(response.data.results.map {
                    val regexMatch = regexPattern.find(it.url)
                    People(regexMatch?.destructured?.component1()?.toInt() ?: -1, it.name)
                })
            }
            else -> {
                DataState.error()
            }
        }
    }
}