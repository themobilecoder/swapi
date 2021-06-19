package com.rafaelds.swapi.data.model.films

import com.rafaelds.swapi.data.TestData.FILM
import com.rafaelds.swapi.data.TestData.FILM_DTO
import org.junit.Test
import kotlin.test.assertEquals

class FilmsDtoToFilmListMapperTest {
    private val filmsDtoTofilmListMapper = FilmsDtoToFilmListMapper()

    @Test
    fun `should convert correctly dto with app uri`() {
        val url = "http://films/42"
        val peopleDto = FilmsDTO(1, "next", listOf(FILM_DTO.copy(url = url)), "prev")
        val expectedUrl = "swapi://films/42"
        assertEquals(listOf(FILM.copy(appUri = expectedUrl)), filmsDtoTofilmListMapper.convert(peopleDto))
    }

}