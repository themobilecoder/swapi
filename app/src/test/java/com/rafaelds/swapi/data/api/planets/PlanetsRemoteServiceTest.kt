package com.rafaelds.swapi.data.api.planets

import com.nhaarman.mockitokotlin2.*
import com.rafaelds.swapi.data.TestData.PLANET_DTO
import com.rafaelds.swapi.data.model.planets.PlanetsDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

class PlanetsRemoteServiceTest {
    private val networkRequestHelper: NetworkRequestHelper = mock()

    private val planetsRemoteService = PlanetsRemoteService(networkRequestHelper)

    @Test
    fun `should return correct data`() {
        runBlocking {
            val results = listOf(PLANET_DTO)
            val expectedPlanetDTO = PlanetsDTO(1, "next", results, "prev")
            val networkResponse = NetworkRequestHelper.NetworkResponse.Success(expectedPlanetDTO)
            whenever(networkRequestHelper.request(any(), eq(PlanetsDTO.serializer()))).thenReturn(networkResponse)

            val actualResult: PlanetsDTO = planetsRemoteService.fetchData("someuri")

            assertEquals(expectedPlanetDTO, actualResult)
            verify(networkRequestHelper).request("someuri", PlanetsDTO.serializer())
        }
    }

    @Test(expected = RemoteService.RemoteServiceException::class)
    fun `should throw exception on error`() {
        runBlocking {
            val networkResponse = NetworkRequestHelper.NetworkResponse.Error<PlanetsDTO>("")
            whenever(networkRequestHelper.request(any(), eq(PlanetsDTO.serializer()))).thenReturn(networkResponse)

            planetsRemoteService.fetchData("someuri")

        }
    }
}