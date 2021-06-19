package com.rafaelds.swapi.data.model.starships

import com.rafaelds.swapi.data.model.LinksData

data class Starship(
    val id: Int,
    val appUri: String,
    val name: String,
    val model: String,
    val starshipClass: String,
    val manufacturer: String,
    val costInCredits: String,
    val length: String,
    val crew: String,
    val passengers: String,
    val maxAtmoshperingSpeed: String,
    val hyperdriveRating: String,
    val MGLT: String,
    val cargoCapacity: String,
    val consumables: String,
    val films: List<LinksData> = listOf(),
    val pilots: List<LinksData> = listOf(),
)
