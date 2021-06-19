package com.rafaelds.swapi.data.model.planets

import com.rafaelds.swapi.data.model.LinksData

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
    val residents: List<LinksData> = listOf(),
    val films: List<LinksData> = listOf(),
    val appUri: String
)
