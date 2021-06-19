package com.rafaelds.swapi.data.api.planets

import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.api.ApiUtils.extractYear
import com.rafaelds.swapi.data.api.ApiUtils.toSwapiSchema
import com.rafaelds.swapi.data.api.films.FilmRemoteService
import com.rafaelds.swapi.data.api.people.PersonRemoteService
import com.rafaelds.swapi.data.model.LinksData
import com.rafaelds.swapi.data.model.planets.Planet
import com.rafaelds.swapi.data.network.NetworkConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ExperimentalPagingApi
class PlanetRepository @Inject constructor(
    private val planetRemoteService: PlanetRemoteService,
    private val personRemoteService: PersonRemoteService,
    private val filmRemoteService: FilmRemoteService,
    private val networkConfig: NetworkConfig,
) {

    private val planetsRepositoryUrl: String
        get() = "${networkConfig.baseUri}planets/"

    suspend fun getPlanetDetails(id: Int): Planet {

        return withContext(Dispatchers.IO) {
            val url = "$planetsRepositoryUrl$id"
            val planetDTO = planetRemoteService.fetchData(url)
            val peopleDeferred = async { planetDTO.residents.map { personRemoteService.fetchData(it) } }
            val filmsDeferred = async { planetDTO.films.map { filmRemoteService.fetchData(it) } }
            coroutineScope {
                Planet(
                    id = id,
                    name = planetDTO.name,
                    appUri = planetDTO.url.toSwapiSchema(),
                    rotationPeriod = planetDTO.rotation_period,
                    orbitalPeriod = planetDTO.orbital_period,
                    diameter = planetDTO.diameter,
                    terrain = planetDTO.terrain,
                    surfaceWater = planetDTO.surface_water,
                    population = planetDTO.population,
                    residents = peopleDeferred.await().map { LinksData(it.name, it.url.toSwapiSchema()) },
                    films = filmsDeferred.await().map { LinksData(it.title, it.url.toSwapiSchema(), it.release_date.extractYear(), ) },
                    gravity = planetDTO.gravity
                )
            }

        }

    }

    companion object {
        private const val FIND_ID_REGEX = """(?:planets/)(\d+)"""
    }


}