package com.rafaelds.swapi.data.people

import com.rafaelds.swapi.data.NetworkConfig
import com.rafaelds.swapi.data.NetworkRequestHelper
import com.rafaelds.swapi.data.RemoteService
import com.rafaelds.swapi.ui.people.People
import kotlinx.serialization.KSerializer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRemoteService @Inject constructor(
    private val networkConfig: NetworkConfig,
    networkRequestHelper: NetworkRequestHelper) : RemoteService<PeopleListDTO, List<People>>(networkRequestHelper) {

    companion object {
        private const val PEOPLE_SUB_URI = "people/"
        private val regexPattern = """(?:people/)(\d+)""".toRegex()
    }

    override val url: String
        get() = networkConfig.baseUri + PEOPLE_SUB_URI
    override val outputSerializer: KSerializer<PeopleListDTO>
        get() = PeopleListDTO.serializer()

    override fun dtoToModelConverter(dto: PeopleListDTO): List<People> {
        return dto.results.map { people ->
            val regexMatch = regexPattern.find(people.url)
            People(regexMatch?.destructured?.component1()?.toInt() ?: -1, people.name)
        }
    }
}