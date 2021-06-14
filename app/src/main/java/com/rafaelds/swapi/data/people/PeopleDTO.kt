package com.rafaelds.swapi.data.people

import kotlinx.serialization.Serializable

@Serializable
data class PeopleDTO(val name: String, val url: String)