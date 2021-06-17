package com.rafaelds.swapi.data.api.planets

import com.nhaarman.mockitokotlin2.*
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.model.planets.PlanetDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class PlanetRemoteServiceTest : CoroutineTest() {

    private val networkRequestHelper: NetworkRequestHelper = mock()

    private val planetRemoteService = PlanetRemoteService(networkRequestHelper)

    @Test
    fun `should return correct data on fetch planet`() {
        runBlocking {
            val networkResponse = NetworkRequestHelper.NetworkResponse.Success(PLANET_DTO)
            whenever(networkRequestHelper.request(any(), eq(PlanetDTO.serializer()))).thenReturn(networkResponse)

            val actualResult: PlanetDTO = planetRemoteService.fetchData("someuri")

            assertEquals(PLANET_DTO, actualResult)
            verify(networkRequestHelper).request("someuri", PlanetDTO.serializer())
        }
    }

    @Test(expected = RemoteService.RemoteServiceException::class)
    fun `should throw exception on error`() {
        runBlocking {
            val networkResponse = NetworkRequestHelper.NetworkResponse.Error<PlanetDTO>("")
            whenever(networkRequestHelper.request(any(), eq(PlanetDTO.serializer()))).thenReturn(networkResponse)

            planetRemoteService.fetchData("someuri")

        }
    }

    companion object {

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