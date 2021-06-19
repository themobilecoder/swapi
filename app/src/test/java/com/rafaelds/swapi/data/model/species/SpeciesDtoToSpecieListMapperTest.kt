package com.rafaelds.swapi.data.model.species

import com.rafaelds.swapi.data.TestData.SPECIE
import com.rafaelds.swapi.data.TestData.SPECIE_DTO
import org.junit.Test
import kotlin.test.assertEquals

class SpeciesDtoToSpecieListMapperTest {
    private val speciesDtoToSpeciesListMapper = SpeciesDtoToSpecieListMapper()

    @Test
    fun `should convert correctly dto with app uri`() {
        val url = "http://species/4"
        val speciesDTO = SpeciesDTO(1, "next", listOf(SPECIE_DTO.copy(url = url)), "prev")
        val expectedUrl = "swapi://species/4"
        assertEquals(listOf(SPECIE.copy(appUri = expectedUrl)), speciesDtoToSpeciesListMapper.convert(speciesDTO))
    }

}