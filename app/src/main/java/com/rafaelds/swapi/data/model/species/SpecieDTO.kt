package com.rafaelds.swapi.data.model.species

import kotlinx.serialization.Serializable

@Serializable
data class SpecieDTO(
    val name: String,
    val classification: String,
    val designation: String,
    val average_height: String,
    val average_lifespan: String,
    val eye_colors: String,
    val hair_colors: String,
    val skin_colors: String,
    val language: String,
    val homeworld: String?,
    val films: List<String>,
    val people: List<String>,
    val url: String,
)
