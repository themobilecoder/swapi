package com.rafaelds.swapi.data.api.people

import com.nhaarman.mockitokotlin2.*
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.TestData.PERSON_DTO
import com.rafaelds.swapi.data.model.people.PeopleDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class PeopleRemoteServiceTest : CoroutineTest() {

    private val networkRequestHelper: NetworkRequestHelper = mock()

    private val peopleRemoteService = PeopleRemoteService(networkRequestHelper)

    @Test
    fun `should fetch correct data`() {
        runBlocking {
            val results = listOf(PERSON_DTO)
            val expectedPeopleDTO = PeopleDTO(1, "next", results, "prev")
            val networkResponse = NetworkRequestHelper.NetworkResponse.Success(expectedPeopleDTO)
            whenever(networkRequestHelper.request(any(), eq(PeopleDTO.serializer()))).thenReturn(networkResponse)

            val actualResult: PeopleDTO = peopleRemoteService.fetchData("someuri")

            assertEquals(expectedPeopleDTO, actualResult)
            verify(networkRequestHelper).request("someuri", PeopleDTO.serializer())
        }
    }

    @Test(expected = RemoteService.RemoteServiceException::class)
    fun `should throw exception on error`() {
        runBlocking {
            val networkResponse = NetworkRequestHelper.NetworkResponse.Error<PeopleDTO>("")
            whenever(networkRequestHelper.request(any(), eq(PeopleDTO.serializer()))).thenReturn(networkResponse)

            peopleRemoteService.fetchData("someuri")

        }
    }

}