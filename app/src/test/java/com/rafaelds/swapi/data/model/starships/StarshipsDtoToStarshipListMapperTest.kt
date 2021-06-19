package com.rafaelds.swapi.data.model.starships

import com.rafaelds.swapi.data.TestData.STARSHIP
import com.rafaelds.swapi.data.TestData.STARSHIP_DTO
import org.junit.Test
import kotlin.test.assertEquals

class StarshipsDtoToStarshipListMapperTest {
    private val starshipsDtoToStarshipListMapper = StarshipsDtoToStarshipListMapper()

    @Test
    fun `should convert correctly dto with app uri`() {
        val url = "http://starships/4"
        val starshipsDto = StarshipsDTO(1, "next", listOf(STARSHIP_DTO.copy(url = url)), "prev")
        val expectedUrl = "swapi://starships/4"
        assertEquals(listOf(STARSHIP.copy(appUri = expectedUrl)), starshipsDtoToStarshipListMapper.convert(starshipsDto))
    }

}