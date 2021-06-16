package com.rafaelds.swapi.data.model.people

import org.junit.Test
import kotlin.test.assertEquals

class PeopleDtoToPersonListMapperTest {
    private val peopleDtoToPersonListMapper = PeopleDtoToPersonListMapper()

    @Test
    fun `should convert correctly dto with app uri`() {
        val peopleDto = PeopleDTO(1, "next", listOf(PERSON_DTO), "prev")
        assertEquals(listOf(PERSON), peopleDtoToPersonListMapper.convert(peopleDto))
    }

    companion object {
        private val PERSON_DTO = PersonDTO("Luke", "male", "http://people/42", "120", "60", "fair", "blue", "blond", "1990", "homeworld")
        private val PERSON = Person(42, "Luke", "male", "swapi://people/42", "120", "60", "fair", "blue", "blond", "1990", "homeworld")
    }
}