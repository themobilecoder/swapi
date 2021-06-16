package com.rafaelds.swapi.data.model.people

data class Person(
    val id: Int,
    val name: String,
    val gender: String,
    val appUri: String,
    val height: String,
    val mass: String,
    val skinColor: String,
    val eyeColor: String,
    val hairColor: String,
    val birthYear: String,
    val home: String
)