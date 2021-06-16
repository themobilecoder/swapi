package com.rafaelds.swapi.data.model.planets

import kotlinx.serialization.Serializable

@Serializable
data class PlanetDTO(
    val name: String,
    val rotation_period: String,
    val orbital_period: String,
    val diameter: String,
    val gravity: String,
    val terrain: String,
    val surface_water: String,
    val population: String,
    val residents: List<String>,
    val films: List<String>,
    val url: String
)
