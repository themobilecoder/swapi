package com.rafaelds.swapi.data.model.vehicles

import com.rafaelds.swapi.data.model.films.FilmData
import com.rafaelds.swapi.data.model.people.PersonData

data class Vehicle(
    val id: Int,
    val appUri: String,
    val name: String,
    val model: String,
    val vehicleClass: String,
    val manufacturer: String,
    val costInCredits: String,
    val length: String,
    val crew: String,
    val passengers: String,
    val maxAtmoshperingSpeed: String,
    val cargoCapacity: String,
    val consumables: String,
    val films: List<FilmData> = listOf(),
    val pilots: List<PersonData> = listOf(),
)
