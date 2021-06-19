package com.rafaelds.swapi.data.model.species

import kotlinx.serialization.Serializable

@Serializable
data class SpeciesDTO(val count: Int, val next: String?, val results: List<SpecieDTO>, val previous: String?)