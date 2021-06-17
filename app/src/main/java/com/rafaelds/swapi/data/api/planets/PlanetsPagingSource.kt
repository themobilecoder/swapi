package com.rafaelds.swapi.data.api.planets

import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.api.BasePagingSource
import com.rafaelds.swapi.data.model.planets.Planet
import com.rafaelds.swapi.data.model.planets.PlanetsDTO
import com.rafaelds.swapi.data.model.planets.PlanetsDtoToPlanetListMapper

@ExperimentalPagingApi
class PlanetsPagingSource constructor(
    uri: String,
    planetsRemoteService: PlanetsRemoteService,
    mapper: PlanetsDtoToPlanetListMapper
) : BasePagingSource<Planet, PlanetsDTO>(uri, planetsRemoteService, mapper) {

    override fun getPreviousKey(pageNumber: Int, response: PlanetsDTO): Int? {
        return if (response.previous != null) pageNumber - 1 else null
    }

    override fun getNextKey(pageNumber: Int, response: PlanetsDTO): Int? {
        return if (response.next != null) pageNumber + 1 else null
    }

}