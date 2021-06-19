package com.rafaelds.swapi.data.model.films

import com.rafaelds.swapi.data.model.people.PersonData
import com.rafaelds.swapi.data.model.planets.PlanetData
import com.rafaelds.swapi.data.model.species.SpecieData
import com.rafaelds.swapi.data.model.starships.StarshipData
import com.rafaelds.swapi.data.model.vehicles.VehicleData

data class Film(
    val id: Int,
    val appUri: String,
    val title: String,
    val episode: String,
    val director: String,
    val producer: String,
    val releaseDate: String,
    val species: List<SpecieData>,
    val starships: List<StarshipData>,
    val vehicles: List<VehicleData>,
    val characters: List<PersonData>,
    val planets: List<PlanetData>,
)