package com.rafaelds.swapi.data.api.species

import com.nhaarman.mockitokotlin2.*
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.TestData.SPECIE_DTO
import com.rafaelds.swapi.data.model.species.SpecieDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class SpecieRemoteServiceTest : CoroutineTest() {

    private val networkRequestHelper: NetworkRequestHelper = mock()

    private val specieRemoteService = SpecieRemoteService(networkRequestHelper)

    @Test
    fun `should fetch correct data`() {
        runBlocking {
            val networkResponse = NetworkRequestHelper.NetworkResponse.Success(SPECIE_DTO)
            whenever(networkRequestHelper.request(any(), eq(SpecieDTO.serializer()))).thenReturn(networkResponse)

            val actualResult: SpecieDTO = specieRemoteService.fetchData("someuri")

            assertEquals(SPECIE_DTO, actualResult)
            verify(networkRequestHelper).request("someuri", SpecieDTO.serializer())
        }
    }

    @Test(expected = RemoteService.RemoteServiceException::class)
    fun `should throw exception on error`() {
        runBlocking {
            val networkResponse = NetworkRequestHelper.NetworkResponse.Error<SpecieDTO>("")
            whenever(networkRequestHelper.request(any(), eq(SpecieDTO.serializer()))).thenReturn(networkResponse)

            specieRemoteService.fetchData("someuri")

        }
    }

}