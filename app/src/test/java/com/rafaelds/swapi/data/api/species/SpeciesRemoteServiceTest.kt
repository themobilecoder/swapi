package com.rafaelds.swapi.data.api.species

import com.nhaarman.mockitokotlin2.*
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.TestData.SPECIE_DTO
import com.rafaelds.swapi.data.model.species.SpeciesDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class SpeciesRemoteServiceTest : CoroutineTest() {

    private val networkRequestHelper: NetworkRequestHelper = mock()

    private val speciesRemoteService = SpeciesRemoteService(networkRequestHelper)

    @Test
    fun `should fetch correct data`() {
        runBlocking {
            val results = listOf(SPECIE_DTO)
            val expectedSpeciesDTO = SpeciesDTO(1, "next", results, "prev")
            val networkResponse = NetworkRequestHelper.NetworkResponse.Success(expectedSpeciesDTO)
            whenever(networkRequestHelper.request(any(), eq(SpeciesDTO.serializer()))).thenReturn(networkResponse)

            val actualResult: SpeciesDTO = speciesRemoteService.fetchData("someuri")

            assertEquals(expectedSpeciesDTO, actualResult)
            verify(networkRequestHelper).request("someuri", SpeciesDTO.serializer())
        }
    }

    @Test(expected = RemoteService.RemoteServiceException::class)
    fun `should throw exception on error`() {
        runBlocking {
            val networkResponse = NetworkRequestHelper.NetworkResponse.Error<SpeciesDTO>("")
            whenever(networkRequestHelper.request(any(), eq(SpeciesDTO.serializer()))).thenReturn(networkResponse)

            speciesRemoteService.fetchData("someuri")

        }
    }

}