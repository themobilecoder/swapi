package com.rafaelds.swapi.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.network.NetworkConfig
import com.rafaelds.swapi.data.people.PeopleDTO
import com.rafaelds.swapi.data.people.PeoplePagingSource
import com.rafaelds.swapi.data.people.PeopleRemoteService
import com.rafaelds.swapi.data.local.Person
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import kotlin.test.assertEquals

@ExperimentalPagingApi
@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class PeoplePagingSourceTest : CoroutineTest() {

    private val peopleRemoteService: PeopleRemoteService = mock()
    private val networkConfig: NetworkConfig = mock()

    private val peoplePagingSource = PeoplePagingSource(peopleRemoteService, networkConfig)


    @Test
    fun `should get data successfully`() {
        runBlocking {
            whenever(peopleRemoteService.fetchData(anyString())).thenReturn(PeopleDTO(0, "", listOf(), null))
            whenever(networkConfig.baseUri).thenReturn("https://this.uri")
            val expectedData = listOf(Person(1, "Luke"))
            whenever(peopleRemoteService.dtoToModelConverter(any())).thenReturn(expectedData)
            val mockParams: PagingSource.LoadParams<Int> = mock()

            val actual: PagingSource.LoadResult<Int, Person> = peoplePagingSource.load(mockParams)

            val expected =  PagingSource.LoadResult.Page(
                data = expectedData,
                prevKey = null,
                nextKey = 2
            )
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `should return error on exception`() {
        runBlocking {
            whenever(networkConfig.baseUri).thenReturn("https://this.uri")
            val exception = RuntimeException()
            whenever(peopleRemoteService.fetchData(anyString())).thenThrow(exception)
            val mockParams: PagingSource.LoadParams<Int> = mock()

            val actual: PagingSource.LoadResult<Int, Person> = peoplePagingSource.load(mockParams)

            assertEquals(PagingSource.LoadResult.Error(exception), actual)
        }
    }
}