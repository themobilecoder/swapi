package com.rafaelds.swapi.data.model.planets

import kotlinx.serialization.Serializable

@Serializable
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
    val residents: List<String>,
    val films: List<String>,
    val appUri: String
)
