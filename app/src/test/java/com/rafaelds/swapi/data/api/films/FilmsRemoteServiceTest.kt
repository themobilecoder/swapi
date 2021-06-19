package com.rafaelds.swapi.data.api.films

import com.nhaarman.mockitokotlin2.*
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.TestData.FILM_DTO
import com.rafaelds.swapi.data.model.films.FilmsDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class FilmsRemoteServiceTest : CoroutineTest() {

    private val networkRequestHelper: NetworkRequestHelper = mock()

    private val filmsRemoteService = FilmsRemoteService(networkRequestHelper)

    @Test
    fun `should fetch correct data`() {
        runBlocking {
            val results = listOf(FILM_DTO)
            val expectedFilmsDTO = FilmsDTO(1, "next", results, "prev")
            val networkResponse = NetworkRequestHelper.NetworkResponse.Success(expectedFilmsDTO)
            whenever(networkRequestHelper.request(any(), eq(FilmsDTO.serializer()))).thenReturn(networkResponse)

            val actualResult: FilmsDTO = filmsRemoteService.fetchData("someuri")

            assertEquals(expectedFilmsDTO, actualResult)
            verify(networkRequestHelper).request("someuri", FilmsDTO.serializer())
        }
    }

    @Test(expected = RemoteService.RemoteServiceException::class)
    fun `should throw exception on error`() {
        runBlocking {
            val networkResponse = NetworkRequestHelper.NetworkResponse.Error<FilmsDTO>("")
            whenever(networkRequestHelper.request(any(), eq(FilmsDTO.serializer()))).thenReturn(networkResponse)

            filmsRemoteService.fetchData("someuri")

        }
    }

}