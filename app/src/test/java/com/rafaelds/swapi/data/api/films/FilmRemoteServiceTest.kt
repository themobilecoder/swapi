package com.rafaelds.swapi.data.api.films

import com.nhaarman.mockitokotlin2.*
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.TestData.FILM_DTO
import com.rafaelds.swapi.data.model.films.FilmDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class FilmRemoteServiceTest : CoroutineTest() {

    private val networkRequestHelper: NetworkRequestHelper = mock()

    private val filmRemoteService = FilmRemoteService(networkRequestHelper)

    @Test
    fun `should fetch correct data`() {
        runBlocking {
            val networkResponse = NetworkRequestHelper.NetworkResponse.Success(FILM_DTO)
            whenever(networkRequestHelper.request(any(), eq(FilmDTO.serializer()))).thenReturn(networkResponse)

            val actualResult: FilmDTO = filmRemoteService.fetchData("someuri")

            assertEquals(FILM_DTO, actualResult)
            verify(networkRequestHelper).request("someuri", FilmDTO.serializer())
        }
    }

    @Test(expected = RemoteService.RemoteServiceException::class)
    fun `should throw exception on error`() {
        runBlocking {
            val networkResponse = NetworkRequestHelper.NetworkResponse.Error<FilmDTO>("")
            whenever(networkRequestHelper.request(any(), eq(FilmDTO.serializer()))).thenReturn(networkResponse)

            filmRemoteService.fetchData("someuri")

        }
    }

}