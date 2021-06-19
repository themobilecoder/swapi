package com.rafaelds.swapi.data.api.planets

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rafaelds.swapi.data.model.planets.Planet
import com.rafaelds.swapi.data.model.planets.PlanetsDtoToPlanetListMapper
import com.rafaelds.swapi.data.network.NetworkConfig
import javax.inject.Inject

@ExperimentalPagingApi
class PlanetsRepository @Inject constructor(
    private val planetsRemoteService: PlanetsRemoteService,
    private val networkConfig: NetworkConfig,
    private val planetsDtoToPlanetListMapper: PlanetsDtoToPlanetListMapper,
) {

    private val repositoryUrl: String
        get() = "${networkConfig.baseUri}planets/"

    fun getPlanetList(): Pager<Int, Planet> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { PlanetsPagingSource(repositoryUrl, planetsRemoteService, planetsDtoToPlanetListMapper) }
        )
    }

}