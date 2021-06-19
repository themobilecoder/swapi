package com.rafaelds.swapi.data.model.species

import com.rafaelds.swapi.data.model.LinksData
import com.rafaelds.swapi.data.model.planets.PlanetData

data class Specie(
    val id: Int,
    val appUri: String,
    val name: String,
    val classification: String,
    val designation: String,
    val averageHeight: String,
    val averageLifespan: String,
    val eyeColors: String,
    val hairColors: String,
    val skinColors: String,
    val language: String,
    val homeworld: PlanetData? = null,
    val films: List<LinksData> = listOf(),
    val people: List<LinksData> = listOf(),
)
