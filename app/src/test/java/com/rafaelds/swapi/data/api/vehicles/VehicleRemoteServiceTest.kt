package com.rafaelds.swapi.data.api.vehicles

import com.nhaarman.mockitokotlin2.*
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.TestData.VEHICLE_DTO
import com.rafaelds.swapi.data.model.vehicles.VehicleDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class VehicleRemoteServiceTest : CoroutineTest() {

    private val networkRequestHelper: NetworkRequestHelper = mock()

    private val vehicleRemoteService = VehicleRemoteService(networkRequestHelper)

    @Test
    fun `should fetch correct data`() {
        runBlocking {
            val networkResponse = NetworkRequestHelper.NetworkResponse.Success(VEHICLE_DTO)
            whenever(networkRequestHelper.request(any(), eq(VehicleDTO.serializer()))).thenReturn(networkResponse)

            val actualResult: VehicleDTO = vehicleRemoteService.fetchData("someuri")

            assertEquals(VEHICLE_DTO, actualResult)
            verify(networkRequestHelper).request("someuri", VehicleDTO.serializer())
        }
    }

    @Test(expected = RemoteService.RemoteServiceException::class)
    fun `should throw exception on error`() {
        runBlocking {
            val networkResponse = NetworkRequestHelper.NetworkResponse.Error<VehicleDTO>("")
            whenever(networkRequestHelper.request(any(), eq(VehicleDTO.serializer()))).thenReturn(networkResponse)

            vehicleRemoteService.fetchData("someuri")

        }
    }

}