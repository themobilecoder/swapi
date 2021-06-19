package com.rafaelds.swapi.data.model.people

import com.rafaelds.swapi.data.model.films.FilmData
import com.rafaelds.swapi.data.model.planets.PlanetData
import com.rafaelds.swapi.data.model.species.SpecieData
import com.rafaelds.swapi.data.model.starships.StarshipData
import com.rafaelds.swapi.data.model.vehicles.VehicleData

data class Person(
    val id: Int,
    val name: String,
    val gender: String,
    val appUri: String,
    val height: String,
    val mass: String,
    val skinColor: String,
    val eyeColor: String,
    val hairColor: String,
    val birthYear: String,
    val planetData: PlanetData? = null,
    val vehicles: List<VehicleData> = listOf(),
    val starships: List<StarshipData> = listOf(),
    val species: List<SpecieData> = listOf(),
    val films: List<FilmData> = listOf(),
)