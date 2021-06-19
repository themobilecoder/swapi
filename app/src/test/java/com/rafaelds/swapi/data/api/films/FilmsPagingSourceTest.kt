package com.rafaelds.swapi.data.api.films

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.TestData.FILM
import com.rafaelds.swapi.data.TestData.FILM_DTO
import com.rafaelds.swapi.data.model.films.Film
import com.rafaelds.swapi.data.model.films.FilmsDTO
import com.rafaelds.swapi.data.model.films.FilmsDtoToFilmListMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.ArgumentMatchers
import kotlin.test.assertEquals

@ExperimentalPagingApi
@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class FilmsPagingSourceTest : CoroutineTest() {

    private val filmsRemoteService: FilmsRemoteService = mock()
    private val mapper: FilmsDtoToFilmListMapper = mock()

    private val filmsPagingSource = FilmsPagingSource("https://this.uri", filmsRemoteService, mapper)

    @Test
    fun `should get data successfully`() {
        runBlocking {
            val expectedData = listOf(FILM)
            whenever(mapper.convert(any())).thenReturn(expectedData)
            whenever(filmsRemoteService.fetchData(ArgumentMatchers.anyString())).thenReturn(
                FilmsDTO(
                    0,
                    "",
                    listOf(FILM_DTO),
                    null
                )
            )
            val mockParams: PagingSource.LoadParams<Int> = mock()

            val actual = filmsPagingSource.load(mockParams)

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
            whenever(filmsRemoteService.fetchData(ArgumentMatchers.anyString())).thenThrow(exception)

            val actual: PagingSource.LoadResult<Int, Film> = filmsPagingSource.load(mockParams)

            assertEquals(PagingSource.LoadResult.Error(exception), actual)
        }
    }

}