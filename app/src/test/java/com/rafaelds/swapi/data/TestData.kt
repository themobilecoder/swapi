package com.rafaelds.swapi.data

import com.rafaelds.swapi.data.model.people.Person
import com.rafaelds.swapi.data.model.people.PersonDTO
import com.rafaelds.swapi.data.model.planets.Planet
import com.rafaelds.swapi.data.model.planets.PlanetDTO

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
        planetData = null
    )
    val PLANET = Planet(
        id = 42,
        name = "tatooine",
        rotationPeriod = "1",
        orbitalPeriod = "2",
        diameter = "3",
        gravity = "4",
        terrain = "terrain",
        surfaceWater = "5",
        population = "100",
        residents = listOf(),
        films = listOf(),
        appUri = "swapi://planets/42"
    )

    val PLANET_DTO = PlanetDTO(
        name = "tatooine",
        rotation_period = "1",
        orbital_period = "2",
        diameter = "3",
        gravity = "4",
        terrain = "terrain",
        surface_water = "5",
        population = "100",
        residents = listOf("resident"),
        films = listOf("film"),
        url = "http://planets/42"
    )
}