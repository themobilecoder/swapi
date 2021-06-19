package com.rafaelds.swapi.data.api.vehicles

import com.nhaarman.mockitokotlin2.*
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.TestData.VEHICLE_DTO
import com.rafaelds.swapi.data.model.vehicles.VehiclesDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class VehiclesRemoteServiceTest : CoroutineTest() {

    private val networkRequestHelper: NetworkRequestHelper = mock()

    private val vehiclesRemoteService = VehiclesRemoteService(networkRequestHelper)

    @Test
    fun `should fetch correct data`() {
        runBlocking {
            val results = listOf(VEHICLE_DTO)
            val expectedVehiclesDTO = VehiclesDTO(1, "next", results, "prev")
            val networkResponse = NetworkRequestHelper.NetworkResponse.Success(expectedVehiclesDTO)
            whenever(networkRequestHelper.request(any(), eq(VehiclesDTO.serializer()))).thenReturn(networkResponse)

            val actualResult: VehiclesDTO = vehiclesRemoteService.fetchData("someuri")

            assertEquals(expectedVehiclesDTO, actualResult)
            verify(networkRequestHelper).request("someuri", VehiclesDTO.serializer())
        }
    }

    @Test(expected = RemoteService.RemoteServiceException::class)
    fun `should throw exception on error`() {
        runBlocking {
            val networkResponse = NetworkRequestHelper.NetworkResponse.Error<VehiclesDTO>("")
            whenever(networkRequestHelper.request(any(), eq(VehiclesDTO.serializer()))).thenReturn(networkResponse)

            vehiclesRemoteService.fetchData("someuri")

        }
    }

}