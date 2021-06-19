package com.rafaelds.swapi.data.model.vehicles

import kotlinx.serialization.Serializable

@Serializable
data class VehicleDTO(
    val name: String,
    val model: String,
    val vehicle_class: String,
    val manufacturer: String,
    val length: String,
    val cost_in_credits: String,
    val crew: String,
    val passengers: String,
    val max_atmosphering_speed: String,
    val cargo_capacity: String,
    val consumables: String,
    val films: List<String>,
    val pilots: List<String>,
    val url: String
)
