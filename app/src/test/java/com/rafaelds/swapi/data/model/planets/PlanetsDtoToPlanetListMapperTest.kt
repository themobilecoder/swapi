package com.rafaelds.swapi.data.model.planets

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

    companion object {
        private val PLANET = Planet(
            id = 42,
            name = "tatooine",
            rotationPeriod = "1",
            orbitalPeriod = "2",
            diameter = "3",
            gravity = "4",
            terrain = "terrain",
            surfaceWater = "5",
            population = "100",
            residents = listOf("resident"),
            films = listOf("film"),
            appUri = "swapi://planets/42"
        )

        private val PLANET_DTO = PlanetDTO(
            name = "tatooine",
            rotation_period = "1",
            orbital_period = "2",
            diameter = "3",
            gravity = "4",
            terrain = "terrain",
            surface_water = "5",
            population = "100",
            residents = listOf("resident"),
            films = listOf("film"),
            url = "http://planets/42"
        )
    }
}