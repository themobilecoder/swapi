package com.rafaelds.swapi.data.model.films

import com.rafaelds.swapi.data.model.LinksData

data class Film(
    val id: Int,
    val appUri: String,
    val title: String,
    val episode: String? = null,
    val director: String,
    val producer: String,
    val releaseDate: String,
    val species: List<LinksData>,
    val starships: List<LinksData>,
    val vehicles: List<LinksData>,
    val characters: List<LinksData>,
    val planets: List<LinksData>,
)