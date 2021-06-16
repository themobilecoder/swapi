package com.rafaelds.swapi.data.api.planets

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.rafaelds.swapi.data.model.planets.Planet
import com.rafaelds.swapi.data.model.planets.PlanetsDtoToPlanetListMapper
import com.rafaelds.swapi.data.network.NetworkConfig
import javax.inject.Inject

@ExperimentalPagingApi
class PlanetsRepository @Inject constructor(
    private val planetsRemoteService: PlanetsRemoteService,
    private val networkConfig: NetworkConfig,
    private val planetsDtoToPlanetListMapper: PlanetsDtoToPlanetListMapper
) {
    fun getPlanetList(): LiveData<PagingData<Planet>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { PlanetsPagingSource(planetsRemoteService, networkConfig, planetsDtoToPlanetListMapper) }
        ).liveData
    }
}