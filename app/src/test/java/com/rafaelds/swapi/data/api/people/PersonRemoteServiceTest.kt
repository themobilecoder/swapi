package com.rafaelds.swapi.data.api.people

import com.nhaarman.mockitokotlin2.*
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.TestData.PERSON_DTO
import com.rafaelds.swapi.data.model.people.PersonDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class PersonRemoteServiceTest : CoroutineTest() {

    private val networkRequestHelper: NetworkRequestHelper = mock()

    private val personRemoteService = PersonRemoteService(networkRequestHelper)

    @Test
    fun `should fetch correct data`() {
        runBlocking {
            val networkResponse = NetworkRequestHelper.NetworkResponse.Success(PERSON_DTO)
            whenever(networkRequestHelper.request(any(), eq(PersonDTO.serializer()))).thenReturn(networkResponse)

            val actualResult: PersonDTO = personRemoteService.fetchData("someuri")

            assertEquals(PERSON_DTO, actualResult)
            verify(networkRequestHelper).request("someuri", PersonDTO.serializer())
        }
    }

    @Test(expected = RemoteService.RemoteServiceException::class)
    fun `should throw exception on error`() {
        runBlocking {
            val networkResponse = NetworkRequestHelper.NetworkResponse.Error<PersonDTO>("")
            whenever(networkRequestHelper.request(any(), eq(PersonDTO.serializer()))).thenReturn(networkResponse)

            personRemoteService.fetchData("someuri")

        }
    }

}