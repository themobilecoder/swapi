package com.rafaelds.swapi.data.model.people

import com.rafaelds.swapi.data.model.LinksData
import com.rafaelds.swapi.data.model.planets.PlanetData

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
    val vehicles: List<LinksData> = listOf(),
    val starships: List<LinksData> = listOf(),
    val species: List<LinksData> = listOf(),
    val films: List<LinksData> = listOf(),
)