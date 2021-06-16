package com.rafaelds.swapi.data.model.people

import kotlinx.serialization.Serializable

@Serializable
data class PeopleDTO(val count: Int, val next: String?, val results: List<PersonDTO>, val previous: String?)