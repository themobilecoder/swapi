package com.rafaelds.swapi.data.api.planets

import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.api.ApiUtils.toSwapiSchema
import com.rafaelds.swapi.data.model.planets.Planet
import com.rafaelds.swapi.data.network.NetworkConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ExperimentalPagingApi
class PlanetRepository @Inject constructor(
    private val planetRemoteService: PlanetRemoteService,
    private val networkConfig: NetworkConfig,
) {

    private val planetsRepositoryUrl: String
        get() = "${networkConfig.baseUri}planets/"

    suspend fun getPlanetDetails(id: Int): Planet {

        return withContext(Dispatchers.IO) {
            val url = "$planetsRepositoryUrl$id"
            val planetDTO = planetRemoteService.fetchData(url)
            val regexMatch = FIND_ID_REGEX.toRegex().find(planetDTO.url)
            Planet(
                id = regexMatch?.destructured?.component1()?.toInt() ?: -1,
                name = planetDTO.name,
                appUri = planetDTO.url.toSwapiSchema(),
                rotationPeriod = planetDTO.rotation_period,
                orbitalPeriod = planetDTO.orbital_period,
                diameter = planetDTO.diameter,
                terrain = planetDTO.terrain,
                surfaceWater = planetDTO.surface_water,
                population = planetDTO.population,
                residents = listOf(),
                films = listOf(),
                gravity = planetDTO.gravity
            )
        }

    }

    companion object {
        private const val FIND_ID_REGEX = """(?:planets/)(\d+)"""
    }


}