package com.rafaelds.swapi.data.model.starships

import kotlinx.serialization.Serializable

@Serializable
data class StarshipsDTO(val count: Int, val next: String?, val results: List<StarshipDTO>, val previous: String?)