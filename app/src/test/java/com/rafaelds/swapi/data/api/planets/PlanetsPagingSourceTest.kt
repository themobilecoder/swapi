package com.rafaelds.swapi.data.api.planets

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.TestData.PLANET
import com.rafaelds.swapi.data.TestData.PLANET_DTO
import com.rafaelds.swapi.data.model.planets.Planet
import com.rafaelds.swapi.data.model.planets.PlanetsDTO
import com.rafaelds.swapi.data.model.planets.PlanetsDtoToPlanetListMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.ArgumentMatchers
import kotlin.test.assertEquals

@ExperimentalPagingApi
@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class PlanetsPagingSourceTest : CoroutineTest() {
    private val planetsRemoteService: PlanetsRemoteService = mock()
    private val mapper: PlanetsDtoToPlanetListMapper = mock()

    private val planetsPagingSource = PlanetsPagingSource("https://this.uri", planetsRemoteService, mapper)

    @Test
    fun `should get data successfully`() {
        runBlocking {
            val expectedData = listOf(PLANET)
            whenever(mapper.convert(any())).thenReturn(expectedData)
            whenever(planetsRemoteService.fetchData(ArgumentMatchers.anyString())).thenReturn(
                PlanetsDTO(
                    0,
                    "",
                    listOf(PLANET_DTO),
                    null
                )
            )
            val mockParams: PagingSource.LoadParams<Int> = mock()

            val actual = planetsPagingSource.load(mockParams)

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
            whenever(planetsRemoteService.fetchData(ArgumentMatchers.anyString())).thenThrow(exception)

            val actual: PagingSource.LoadResult<Int, Planet> = planetsPagingSource.load(mockParams)

            assertEquals(PagingSource.LoadResult.Error(exception), actual)
        }
    }

}