package com.rafaelds.swapi.data.model.people

import org.junit.Test
import kotlin.test.assertEquals

class PersonDtoToPersonMapperTest {
    private val personDtoToPersonListMapper = PersonDtoToPersonMapper()

    @Test
    fun `should convert correctly dto with app uri`() {
        assertEquals(PERSON, personDtoToPersonListMapper.convert(PERSON_DTO))
    }

    companion object {
        private val PERSON_DTO = PersonDTO("Luke", "male", "http://people/42", "120", "60", "fair", "blue", "blond", "1990", "homeworld")
        private val PERSON = Person(42, "Luke", "male", "swapi://people/42", "120", "60", "fair", "blue", "blond", "1990", "homeworld")
    }
}