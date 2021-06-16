package com.rafaelds.swapi.data.api.planets

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rafaelds.swapi.data.model.planets.Planet
import com.rafaelds.swapi.data.model.planets.PlanetsDtoToPlanetListMapper
import com.rafaelds.swapi.data.network.NetworkConfig

@ExperimentalPagingApi
class PlanetsPagingSource constructor(
    private val planetsRemoteService: PlanetsRemoteService,
    private val networkConfig: NetworkConfig,
    private val mapper: PlanetsDtoToPlanetListMapper
) : PagingSource<Int, Planet>() {

    override fun getRefreshKey(state: PagingState<Int, Planet>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Planet> {
        return try {
            val pageNumber = params.key ?: 1
            val baseUri = "${networkConfig.baseUri}$PLANETS_SUB_URI"
            val response = planetsRemoteService.fetchData("${baseUri}?page=$pageNumber")
            val prevKey = if (response.previous != null) pageNumber - 1 else null
            val nextKey = if (response.next != null) pageNumber + 1 else null
            val data = mapper.convert(response)
            LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    companion object {
        private const val PLANETS_SUB_URI = "planets/"
    }

}