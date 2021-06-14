package com.rafaelds.swapi.data.people

import com.rafaelds.swapi.data.network.NetworkConfig
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import com.rafaelds.swapi.ui.people.Person
import kotlinx.serialization.KSerializer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRemoteService @Inject constructor(
    private val networkConfig: NetworkConfig,
    networkRequestHelper: NetworkRequestHelper
) : RemoteService<PeopleDTO, List<Person>>(networkRequestHelper) {

    companion object {
        private const val PEOPLE_SUB_URI = "people/"
        private val regexPattern = """(?:people/)(\d+)""".toRegex()
    }

    override val url: String
        get() = networkConfig.baseUri + PEOPLE_SUB_URI
    override val outputSerializer: KSerializer<PeopleDTO>
        get() = PeopleDTO.serializer()

    override fun dtoToModelConverter(dto: PeopleDTO): List<Person> {
        return dto.results.map { people ->
            val regexMatch = regexPattern.find(people.url)
            Person(regexMatch?.destructured?.component1()?.toInt() ?: -1, people.name)
        }
    }
}