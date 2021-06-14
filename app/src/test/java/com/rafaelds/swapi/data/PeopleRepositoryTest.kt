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
            whenever(peopleRemoteService.getPeopleList()).thenReturn(expectedResult)

            val actualResult = peopleRepository.getPeopleList()

            assertEquals(expectedResult, actualResult)
            verify(peopleRemoteService).getPeopleList()
        }
    }

    @Test
    fun `regex stuff`() {
        val url = "https://swapi.dev/api/people/421/"
        val regexPattern = """(?:people/)(\d+)""".toRegex()
        val matchResult = regexPattern.find(url)
        assertEquals("421", matchResult!!.destructured.component1())
    }

    @Test
    fun `regex stuff2`() {
        val url = "https://swapi.dev/api/people/421?querystuff"
        val regexPattern = """(?:people/)(\d+)""".toRegex()
        val matchResult = regexPattern.find(url)
        assertEquals("421", matchResult!!.destructured.component1())
    }
}