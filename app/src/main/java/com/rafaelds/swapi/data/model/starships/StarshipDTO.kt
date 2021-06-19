package com.rafaelds.swapi.data.model.starships

import kotlinx.serialization.Serializable

@Serializable
data class StarshipDTO(
    val name: String,
    val model: String,
    val starship_class: String,
    val manufacturer: String,
    val cost_in_credits: String,
    val length: String,
    val crew: String,
    val passengers: String,
    val max_atmosphering_speed: String,
    val hyperdrive_rating: String,
    val MGLT: String,
    val cargo_capacity: String,
    val consumables: String,
    val films: List<String>,
    val pilots: List<String>,
    val url: String,
)
