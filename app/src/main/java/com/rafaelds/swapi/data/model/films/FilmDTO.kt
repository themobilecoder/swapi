package com.rafaelds.swapi.data.model.films

import kotlinx.serialization.Serializable

@Serializable
data class FilmDTO(
    val title: String,
    val episode_id: Int,
    val director: String,
    val producer: String,
    val release_date: String,
    val opening_crawl: String,
    val species: List<String>,
    val starships: List<String>,
    val vehicles: List<String>,
    val characters: List<String>,
    val planets: List<String>,
    val url: String
)
