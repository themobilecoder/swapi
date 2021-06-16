package com.rafaelds.swapi.data.people

import kotlinx.serialization.Serializable

@Serializable
data class PersonDTO(
    val name: String,
    val gender: String,
    val url: String,
    val height: String,
    val mass: String,
    val skin_color: String,
    val eye_color: String,
    val hair_color: String,
    val birth_year: String,
    val homeworld: String
)