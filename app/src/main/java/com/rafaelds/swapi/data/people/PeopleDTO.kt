package com.rafaelds.swapi.data.people

import kotlinx.serialization.Serializable

@Serializable
data class PeopleDTO(val count: Int, val next: String, val results: List<Person>)