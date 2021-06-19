package com.rafaelds.swapi.data.api.films

import com.rafaelds.swapi.data.api.ApiUtils.toReadableDate
import com.rafaelds.swapi.data.api.ApiUtils.toSwapiSchema
import com.rafaelds.swapi.data.api.people.PersonRemoteService
import com.rafaelds.swapi.data.api.planets.PlanetRemoteService
import com.rafaelds.swapi.data.api.species.SpecieRemoteService
import com.rafaelds.swapi.data.api.starships.StarshipRemoteService
import com.rafaelds.swapi.data.api.vehicles.VehicleRemoteService
import com.rafaelds.swapi.data.model.LinksData
import com.rafaelds.swapi.data.model.films.Film
import com.rafaelds.swapi.data.network.NetworkConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilmRepository @Inject constructor(
    private val personRemoteService: PersonRemoteService,
    private val planetRemoteService: PlanetRemoteService,
    private val vehicleRemoteService: VehicleRemoteService,
    private val filmRemoteService: FilmRemoteService,
    private val starshipRemoteService: StarshipRemoteService,
    private val specieRemoteService: SpecieRemoteService,
    private val networkConfig: NetworkConfig,
) {

    private val filmRepositoryUrl: String
        get() = "${networkConfig.baseUri}films/"

    suspend fun getFilmDetails(id: Int): Film {
        return withContext(Dispatchers.IO) {
            val url = "$filmRepositoryUrl$id"
            val filmDTO = filmRemoteService.fetchData(url)
            val peopleDeferred = async { filmDTO.characters.map { personRemoteService.fetchData(it) } }
            val speciesDeferred = async { filmDTO.species.map { specieRemoteService.fetchData(it) } }
            val starshipsDeferred = async { filmDTO.starships.map { starshipRemoteService.fetchData(it) } }
            val vehiclesDeferred = async { filmDTO.vehicles.map { vehicleRemoteService.fetchData(it) } }
            val planetsDeferred = async { filmDTO.planets.map { planetRemoteService.fetchData(it) } }

            coroutineScope {
                Film(
                    id = id,
                    title = filmDTO.title,
                    appUri = filmDTO.url.toSwapiSchema(),
                    species = speciesDeferred.await().map { LinksData(it.name, it.url.toSwapiSchema(), it.classification) },
                    starships = starshipsDeferred.await().map { LinksData(it.name, it.url.toSwapiSchema(), it.starship_class) },
                    vehicles = vehiclesDeferred.await().map { LinksData(it.name, it.url.toSwapiSchema(), it.vehicle_class) },
                    planets = planetsDeferred.await().map { LinksData(it.name, it.url.toSwapiSchema()) },
                    characters = peopleDeferred.await().map { LinksData(it.name, it.url.toSwapiSchema()) },
                    releaseDate = filmDTO.release_date.toReadableDate(),
                    producer = filmDTO.producer,
                    director = filmDTO.director
                )
            }

        }
    }

}