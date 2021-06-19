package com.rafaelds.swapi.data.model.vehicles

import com.rafaelds.swapi.data.TestData.VEHICLE
import com.rafaelds.swapi.data.TestData.VEHICLE_DTO
import org.junit.Test
import kotlin.test.assertEquals

class VehiclesDtoToVehicleListMapperTest {
    private val vehiclesDtoToVehiclesListMapper = VehiclesDtoToVehicleListMapper()

    @Test
    fun `should convert correctly dto with app uri`() {
        val url = "http://vehicles/4"
        val vehiclesDto = VehiclesDTO(1, "next", listOf(VEHICLE_DTO.copy(url = url)), "prev")
        val expectedUrl = "swapi://vehicles/4"
        assertEquals(listOf(VEHICLE.copy(appUri = expectedUrl)), vehiclesDtoToVehiclesListMapper.convert(vehiclesDto))
    }

}