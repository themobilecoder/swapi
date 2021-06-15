package com.rafaelds.swapi.data.people

import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import com.rafaelds.swapi.data.local.Person
import kotlinx.serialization.KSerializer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRemoteService @Inject constructor(networkRequestHelper: NetworkRequestHelper) : RemoteService<PeopleDTO, List<Person>>(networkRequestHelper) {

    companion object {
        private const val FIND_ID_REGEX = """(?:people/)(\d+)"""
    }

    override val outputSerializer: KSerializer<PeopleDTO>
        get() = PeopleDTO.serializer()

    override fun dtoToModelConverter(dto: PeopleDTO): List<Person> {
        return dto.results.map { people ->
            val regexMatch = FIND_ID_REGEX.toRegex().find(people.url)
            Person(regexMatch?.destructured?.component1()?.toInt() ?: -1, people.name)
        }
    }
}