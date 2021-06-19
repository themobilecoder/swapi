package com.rafaelds.swapi.data.model.people

import com.rafaelds.swapi.data.TestData.PERSON
import com.rafaelds.swapi.data.TestData.PERSON_DTO
import org.junit.Test
import kotlin.test.assertEquals

class PeopleDtoToPersonListMapperTest {
    private val peopleDtoToPersonListMapper = PeopleDtoToPersonListMapper()

    @Test
    fun `should convert correctly dto with app uri`() {
        val url = "http://people/42"
        val peopleDto = PeopleDTO(1, "next", listOf(PERSON_DTO.copy(url = url)), "prev")
        val expectedUrl = "swapi://people/42"
        assertEquals(listOf(PERSON.copy(appUri = expectedUrl)), peopleDtoToPersonListMapper.convert(peopleDto))
    }

}