package com.rafaelds.swapi.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.people.PeopleRemoteService
import com.rafaelds.swapi.data.people.PeopleRepository
import com.rafaelds.swapi.ui.people.People
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class PeopleRepositoryTest : CoroutineTest() {

    private val peopleRemoteService: PeopleRemoteService = mock()
    private val peopleRepository = PeopleRepository(peopleRemoteService)

    @Test
    fun `should return people list data state`() {
        runBlocking {
            val expectedResult = DataState.success(listOf(People(1, "Name")))
            whenever(peopleRemoteService.fetchData()).thenReturn(expectedResult)

            val actualResult = peopleRepository.getPeopleList()

            assertEquals(expectedResult, actualResult)
            verify(peopleRemoteService).fetchData()
        }
    }
}