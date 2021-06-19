package com.rafaelds.swapi.data.model.planets

import com.rafaelds.swapi.data.TestData.PLANET
import com.rafaelds.swapi.data.TestData.PLANET_DTO
import org.junit.Test
import kotlin.test.assertEquals

class PlanetsDtoToPlanetListMapperTest {

    private val planetsDtoToPlanetListMapper = PlanetsDtoToPlanetListMapper()

    @Test
    fun `should convert correctly dto with app uri`() {
        val planetsDTO = PlanetsDTO(
            count = 100,
            next = "",
            results = listOf(PLANET_DTO),
            previous = ""
        )
        assertEquals(listOf(PLANET), planetsDtoToPlanetListMapper.convert(planetsDTO))
    }

}