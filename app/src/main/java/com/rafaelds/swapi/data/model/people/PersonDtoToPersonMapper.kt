package com.rafaelds.swapi.data.model.people

import com.rafaelds.swapi.data.model.DtoToModelMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonDtoToPersonMapper @Inject constructor() : DtoToModelMapper<PersonDTO, Person> {
    override fun convert(dto: PersonDTO): Person {
        val regexMatch = FIND_ID_REGEX.toRegex().find(dto.url)
        return Person(
            id = regexMatch?.destructured?.component1()?.toInt() ?: -1,
            name = dto.name,
            appUri = dto.url.replace("http", "swapi"),
            height = dto.height,
            mass = dto.mass,
            skinColor = dto.skin_color,
            eyeColor = dto.eye_color,
            hairColor = dto.hair_color,
            birthYear = dto.birth_year,
            home = dto.homeworld,
            gender = dto.gender
        )
    }

    companion object {
        private const val FIND_ID_REGEX = """(?:people/)(\d+)"""
    }
}