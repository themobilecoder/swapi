package com.rafaelds.swapi.data.model.planets

import com.rafaelds.swapi.data.model.films.FilmData
import com.rafaelds.swapi.data.model.people.PersonData

data class Planet(
    val id: Int,
    val name: String,
    val rotationPeriod: String,
    val orbitalPeriod: String,
    val diameter: String,
    val gravity: String,
    val terrain: String,
    val surfaceWater: String,
    val population: String,
    val residents: List<PersonData> = listOf(),
    val films: List<FilmData> = listOf(),
    val appUri: String
)
