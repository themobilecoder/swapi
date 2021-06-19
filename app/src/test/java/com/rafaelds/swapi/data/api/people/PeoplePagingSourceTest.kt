package com.rafaelds.swapi.data.api.people

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.TestData.PERSON
import com.rafaelds.swapi.data.TestData.PERSON_DTO
import com.rafaelds.swapi.data.model.people.PeopleDTO
import com.rafaelds.swapi.data.model.people.PeopleDtoToPersonListMapper
import com.rafaelds.swapi.data.model.people.Person
import com.rafaelds.swapi.data.model.people.PersonDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.ArgumentMatchers
import kotlin.test.assertEquals

@ExperimentalPagingApi
@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class PeoplePagingSourceTest : CoroutineTest() {

    private val peopleRemoteService: PeopleRemoteService = mock()
    private val mapper: PeopleDtoToPersonListMapper = mock()

    private val peoplePagingSource = PeoplePagingSource("https://this.uri", peopleRemoteService, mapper)

    @Test
    fun `should get data successfully`() {
        runBlocking {
            val expectedData = listOf(PERSON)
            whenever(mapper.convert(any())).thenReturn(expectedData)
            whenever(peopleRemoteService.fetchData(ArgumentMatchers.anyString())).thenReturn(
                PeopleDTO(
                    0,
                    "",
                    listOf(PERSON_DTO),
                    null
                )
            )
            val mockParams: PagingSource.LoadParams<Int> = mock()

            val actual = peoplePagingSource.load(mockParams)

            val expected = PagingSource.LoadResult.Page(
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
            val mockParams: PagingSource.LoadParams<Int> = mock()
            val exception = RuntimeException()
            whenever(peopleRemoteService.fetchData(ArgumentMatchers.anyString())).thenThrow(exception)

            val actual: PagingSource.LoadResult<Int, Person> = peoplePagingSource.load(mockParams)

            assertEquals(PagingSource.LoadResult.Error(exception), actual)
        }
    }

}