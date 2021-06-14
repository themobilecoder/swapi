package com.rafaelds.swapi.data.people

import com.nhaarman.mockitokotlin2.*
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class PeopleRemoteServiceTest : CoroutineTest(){

    private val networkRequestHelper: NetworkRequestHelper = mock()

    private val peopleRemoteService = PeopleRemoteService(networkRequestHelper)

    @Test
    fun `should return correct data on fetch people`() {
        runBlocking {
            val results = listOf(PersonDTO( "Luke", "http://uri/people/42"))
            val expectedPeopleDTO = PeopleDTO(1, "next", results, "prev")
            val networkResponse = NetworkRequestHelper.NetworkResponse.Success(expectedPeopleDTO)
            whenever(networkRequestHelper.request(any(), eq(PeopleDTO.serializer()))).thenReturn(networkResponse)

            val actualResult: PeopleDTO = peopleRemoteService.fetchData("someuri")

            assertEquals(expectedPeopleDTO, actualResult)
            verify(networkRequestHelper).request("someuri", PeopleDTO.serializer())
        }
    }

}