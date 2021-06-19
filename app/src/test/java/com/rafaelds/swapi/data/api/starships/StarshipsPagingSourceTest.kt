package com.rafaelds.swapi.data.api.starships

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.TestData.STARSHIP
import com.rafaelds.swapi.data.TestData.STARSHIP_DTO
import com.rafaelds.swapi.data.model.starships.Starship
import com.rafaelds.swapi.data.model.starships.StarshipsDTO
import com.rafaelds.swapi.data.model.starships.StarshipsDtoToStarshipListMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.ArgumentMatchers
import kotlin.test.assertEquals

@ExperimentalPagingApi
@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class StarshipsPagingSourceTest : CoroutineTest() {

    private val starshipsRemoteService: StarshipsRemoteService = mock()
    private val mapper: StarshipsDtoToStarshipListMapper = mock()

    private val starshipsPagingSource = StarshipsPagingSource("https://this.uri", starshipsRemoteService, mapper)

    @Test
    fun `should get data successfully`() {
        runBlocking {
            val expectedData = listOf(STARSHIP)
            whenever(mapper.convert(any())).thenReturn(expectedData)
            whenever(starshipsRemoteService.fetchData(ArgumentMatchers.anyString())).thenReturn(
                StarshipsDTO(
                    0,
                    "",
                    listOf(STARSHIP_DTO),
                    null
                )
            )
            val mockParams: PagingSource.LoadParams<Int> = mock()

            val actual = starshipsPagingSource.load(mockParams)

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
            whenever(starshipsRemoteService.fetchData(ArgumentMatchers.anyString())).thenThrow(exception)

            val actual: PagingSource.LoadResult<Int, Starship> = starshipsPagingSource.load(mockParams)

            assertEquals(PagingSource.LoadResult.Error(exception), actual)
        }
    }

}