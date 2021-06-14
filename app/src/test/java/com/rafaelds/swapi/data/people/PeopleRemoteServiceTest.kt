package com.rafaelds.swapi.data.people

import com.nhaarman.mockitokotlin2.*
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.DataState
import com.rafaelds.swapi.data.NetworkConfig
import com.rafaelds.swapi.data.NetworkRequestHelper
import com.rafaelds.swapi.ui.people.People
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class PeopleRemoteServiceTest : CoroutineTest(){


    private val networkConfig: NetworkConfig = mock()
    private val networkRequestHelper: NetworkRequestHelper = mock()

    private val peopleRemoteService = PeopleRemoteService(networkConfig, networkRequestHelper)

    @Test
    fun `should return correct data on fetch people`() {
        runBlocking {
            whenever(networkConfig.baseUri).thenReturn("http://uri/")
            val results = listOf(PeopleDTO("Luke", "http://uri/people/42/"))
            val networkResponse = NetworkRequestHelper.NetworkResponse.Success(PeopleListDTO(1, "next", results))
            whenever(networkRequestHelper.request(any(), eq(PeopleListDTO.serializer()))).thenReturn(networkResponse)
            val expectedResult = DataState.success(listOf(People(42, "Luke")))

            val actualResult = peopleRemoteService.fetchData()

            assertEquals(expectedResult, actualResult)
            verify(networkRequestHelper).request("http://uri/people/", PeopleListDTO.serializer())
        }
    }

}