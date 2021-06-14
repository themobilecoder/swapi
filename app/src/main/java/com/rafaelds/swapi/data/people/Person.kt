package com.rafaelds.swapi.data.people

import kotlinx.serialization.Serializable

@Serializable
data class Person(val name: String, val url: String)