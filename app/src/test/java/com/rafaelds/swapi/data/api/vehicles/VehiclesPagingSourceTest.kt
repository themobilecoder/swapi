package com.rafaelds.swapi.data.api.vehicles

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.TestData.VEHICLE
import com.rafaelds.swapi.data.TestData.VEHICLE_DTO
import com.rafaelds.swapi.data.model.vehicles.Vehicle
import com.rafaelds.swapi.data.model.vehicles.VehiclesDTO
import com.rafaelds.swapi.data.model.vehicles.VehiclesDtoToVehicleListMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.ArgumentMatchers
import kotlin.test.assertEquals

@ExperimentalPagingApi
@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class VehiclesPagingSourceTest : CoroutineTest() {

    private val vehiclesRemoteService: VehiclesRemoteService = mock()
    private val mapper: VehiclesDtoToVehicleListMapper = mock()

    private val vehiclesPagingSource = VehiclesPagingSource("https://this.uri", vehiclesRemoteService, mapper)

    @Test
    fun `should get data successfully`() {
        runBlocking {
            val expectedData = listOf(VEHICLE)
            whenever(mapper.convert(any())).thenReturn(expectedData)
            whenever(vehiclesRemoteService.fetchData(ArgumentMatchers.anyString())).thenReturn(
                VehiclesDTO(
                    0,
                    "",
                    listOf(VEHICLE_DTO),
                    null
                )
            )
            val mockParams: PagingSource.LoadParams<Int> = mock()

            val actual = vehiclesPagingSource.load(mockParams)

            val expected = PagingSource.LoadResult.Page(
                data = expectedData,
                prevKey = null,
                nextKey = 2
            )
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `should return error on exception`() {
        runBlocking {
            val mockParams: PagingSource.LoadParams<Int> = mock()
            val exception = RuntimeException()
            whenever(vehiclesRemoteService.fetchData(ArgumentMatchers.anyString())).thenThrow(exception)

            val actual: PagingSource.LoadResult<Int, Vehicle> = vehiclesPagingSource.load(mockParams)

            assertEquals(PagingSource.LoadResult.Error(exception), actual)
        }
    }

}