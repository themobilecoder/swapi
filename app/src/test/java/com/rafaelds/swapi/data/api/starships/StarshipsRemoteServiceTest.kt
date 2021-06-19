package com.rafaelds.swapi.data.api.starships

import com.nhaarman.mockitokotlin2.*
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.TestData.STARSHIP_DTO
import com.rafaelds.swapi.data.model.starships.StarshipsDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class StarshipsRemoteServiceTest : CoroutineTest() {

    private val networkRequestHelper: NetworkRequestHelper = mock()

    private val starshipsRemoteService = StarshipsRemoteService(networkRequestHelper)

    @Test
    fun `should fetch correct data`() {
        runBlocking {
            val results = listOf(STARSHIP_DTO)
            val expectedStarshipsDTO = StarshipsDTO(1, "next", results, "prev")
            val networkResponse = NetworkRequestHelper.NetworkResponse.Success(expectedStarshipsDTO)
            whenever(networkRequestHelper.request(any(), eq(StarshipsDTO.serializer()))).thenReturn(networkResponse)

            val actualResult: StarshipsDTO = starshipsRemoteService.fetchData("someuri")

            assertEquals(expectedStarshipsDTO, actualResult)
            verify(networkRequestHelper).request("someuri", StarshipsDTO.serializer())
        }
    }

    @Test(expected = RemoteService.RemoteServiceException::class)
    fun `should throw exception on error`() {
        runBlocking {
            val networkResponse = NetworkRequestHelper.NetworkResponse.Error<StarshipsDTO>("")
            whenever(networkRequestHelper.request(any(), eq(StarshipsDTO.serializer()))).thenReturn(networkResponse)

            starshipsRemoteService.fetchData("someuri")

        }
    }

}