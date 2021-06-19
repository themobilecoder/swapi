package com.rafaelds.swapi.data.api.species

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.rafaelds.swapi.CoroutineTest
import com.rafaelds.swapi.data.TestData.SPECIE
import com.rafaelds.swapi.data.TestData.SPECIE_DTO
import com.rafaelds.swapi.data.model.species.Specie
import com.rafaelds.swapi.data.model.species.SpeciesDTO
import com.rafaelds.swapi.data.model.species.SpeciesDtoToSpecieListMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.ArgumentMatchers
import kotlin.test.assertEquals

@ExperimentalPagingApi
@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class SpeciesPagingSourceTest : CoroutineTest() {

    private val speciesRemoteService: SpeciesRemoteService = mock()
    private val mapper: SpeciesDtoToSpecieListMapper = mock()

    private val speciesPagingSource = SpeciesPagingSource("https://this.uri", speciesRemoteService, mapper)

    @Test
    fun `should get data successfully`() {
        runBlocking {
            val expectedData = listOf(SPECIE)
            whenever(mapper.convert(any())).thenReturn(expectedData)
            whenever(speciesRemoteService.fetchData(ArgumentMatchers.anyString())).thenReturn(
                SpeciesDTO(
                    0,
                    "",
                    listOf(SPECIE_DTO),
                    null
                )
            )
            val mockParams: PagingSource.LoadParams<Int> = mock()

            val actual = speciesPagingSource.load(mockParams)

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
            whenever(speciesRemoteService.fetchData(ArgumentMatchers.anyString())).thenThrow(exception)

            val actual: PagingSource.LoadResult<Int, Specie> = speciesPagingSource.load(mockParams)

            assertEquals(PagingSource.LoadResult.Error(exception), actual)
        }
    }

}