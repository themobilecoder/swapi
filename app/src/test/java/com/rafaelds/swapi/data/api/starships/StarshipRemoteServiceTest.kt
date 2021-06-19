package com.rafaelds.swapi.data.api.starships

import com.nhaarman.mockitokotlin2.*
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.TestData.STARSHIP_DTO
import com.rafaelds.swapi.data.model.starships.StarshipDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class StarshipRemoteServiceTest : CoroutineTest() {

    private val networkRequestHelper: NetworkRequestHelper = mock()

    private val starshipRemoteService = StarshipRemoteService(networkRequestHelper)

    @Test
    fun `should fetch correct data`() {
        runBlocking {
            val networkResponse = NetworkRequestHelper.NetworkResponse.Success(STARSHIP_DTO)
            whenever(networkRequestHelper.request(any(), eq(StarshipDTO.serializer()))).thenReturn(networkResponse)

            val actualResult: StarshipDTO = starshipRemoteService.fetchData("someuri")

            assertEquals(STARSHIP_DTO, actualResult)
            verify(networkRequestHelper).request("someuri", StarshipDTO.serializer())
        }
    }

    @Test(expected = RemoteService.RemoteServiceException::class)
    fun `should throw exception on error`() {
        runBlocking {
            val networkResponse = NetworkRequestHelper.NetworkResponse.Error<StarshipDTO>("")
            whenever(networkRequestHelper.request(any(), eq(StarshipDTO.serializer()))).thenReturn(networkResponse)

            starshipRemoteService.fetchData("someuri")

        }
    }

}