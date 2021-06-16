package com.rafaelds.swapi.data.people

import com.rafaelds.swapi.data.DtoToModelMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleDtoToPersonListMapper @Inject constructor() : DtoToModelMapper<PeopleDTO, List<Person>> {
    override fun convert(dto: PeopleDTO): List<Person> {
        return dto.results.map { people ->
            val regexMatch = FIND_ID_REGEX.toRegex().find(people.url)
            Person(
                id = regexMatch?.destructured?.component1()?.toInt() ?: -1,
                name = people.name,
                appUri = people.url.replace("http", "swapi"),
                height = people.height,
                mass = people.mass,
                skinColor = people.skin_color,
                eyeColor = people.eye_color,
                hairColor = people.hair_color,
                birthYear = people.birth_year,
                home = people.homeworld,
                gender = people.gender
            )
        }
    }

    companion object {
        private const val FIND_ID_REGEX = """(?:people/)(\d+)"""
    }
}