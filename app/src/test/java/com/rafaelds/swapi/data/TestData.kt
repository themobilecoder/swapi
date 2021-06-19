package com.rafaelds.swapi.data

import com.rafaelds.swapi.data.model.people.Person
import com.rafaelds.swapi.data.model.people.PersonDTO

object TestData {
    val PERSON_DTO = PersonDTO(
        name = "Luke",
        gender = "male",
        url = "http://api/people/42",
        height = "120",
        mass = "60",
        skin_color = "fair",
        eye_color = "blue",
        hair_color = "blond",
        birth_year = "1990",
        homeworld = "http://api/planets/1",
    )
    val PERSON = Person(
        id = 42,
        name = "Luke",
        gender = "male",
        appUri = "swapi://api/people/42",
        height = "120",
        mass = "60",
        skinColor = "fair",
        eyeColor = "blue",
        hairColor = "blond",
        birthYear = "1990",
        homeData = null
    )
}