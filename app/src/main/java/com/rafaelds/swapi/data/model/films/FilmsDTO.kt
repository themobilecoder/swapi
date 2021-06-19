package com.rafaelds.swapi.data.model.films

import kotlinx.serialization.Serializable

@Serializable
data class FilmsDTO(
    val count: Int, val next: String?, val results: List<FilmDTO>, val previous: String?
)
