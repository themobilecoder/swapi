package com.rafaelds.swapi.data.model.planets

import kotlinx.serialization.Serializable

@Serializable
data class PlanetsDTO(val count: Int, val next: String?, val results: List<PlanetDTO>, val previous: String?)