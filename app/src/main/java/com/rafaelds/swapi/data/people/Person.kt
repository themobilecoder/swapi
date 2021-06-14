package com.rafaelds.swapi.data.people

import kotlinx.serialization.Serializable

@Serializable
data class PersonDTO(val name: String, val url: String)