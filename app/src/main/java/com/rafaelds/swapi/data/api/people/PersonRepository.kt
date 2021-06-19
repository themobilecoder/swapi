package com.rafaelds.swapi.data.api.people

import com.rafaelds.swapi.data.api.ApiStringUtils.extractYear
import com.rafaelds.swapi.data.api.ApiStringUtils.toSwapiSchema
import com.rafaelds.swapi.data.api.films.FilmRemoteService
import com.rafaelds.swapi.data.api.planets.PlanetRemoteService
import com.rafaelds.swapi.data.api.species.SpecieRemoteService
import com.rafaelds.swapi.data.api.starships.StarshipRemoteService
import com.rafaelds.swapi.data.api.vehicles.VehicleRemoteService
import com.rafaelds.swapi.data.model.LinksData
import com.rafaelds.swapi.data.model.people.Person
import com.rafaelds.swapi.data.model.planets.PlanetData
import com.rafaelds.swapi.data.network.NetworkConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepository @Inject constructor(
    private val personRemoteService: PersonRemoteService,
    private val planetRemoteService: PlanetRemoteService,
    private val vehicleRemoteService: VehicleRemoteService,
    private val filmRemoteService: FilmRemoteService,
    private val starshipRemoteService: StarshipRemoteService,
    private val specieRemoteService: SpecieRemoteService,
    private val networkConfig: NetworkConfig,
) {

    private val peopleRepositoryUrl: String
        get() = "${networkConfig.baseUri}people/"

    suspend fun getPersonDetails(id: Int): Person {
        return withContext(Dispatchers.IO) {
            val url = "$peopleRepositoryUrl$id"
            val personDTO = personRemoteService.fetchData(url)
            val filmsDeferred = async { personDTO.films.map { filmRemoteService.fetchData(it) } }
            val speciesDeferred = async { personDTO.species.map { specieRemoteService.fetchData(it) } }
            val starshipsDeferred = async { personDTO.starships.map { starshipRemoteService.fetchData(it) } }
            val vehiclesDeferred = async { personDTO.vehicles.map { vehicleRemoteService.fetchData(it) } }
            val homeDeferred = async { personDTO.homeworld?.let { planetRemoteService.fetchData(it) } }

            coroutineScope {
                Person(
                    id = id,
                    name = personDTO.name,
                    appUri = personDTO.url.toSwapiSchema(),
                    height = personDTO.height,
                    mass = personDTO.mass,
                    skinColor = personDTO.skin_color,
                    eyeColor = personDTO.eye_color,
                    hairColor = personDTO.hair_color,
                    birthYear = personDTO.birth_year,
                    gender = personDTO.gender,
                    planetData = homeDeferred.await()?.let { PlanetData(name = it.name, url = it.url.toSwapiSchema()) },
                    films = filmsDeferred.await().map { LinksData(it.title, it.url.toSwapiSchema(), it.release_date.extractYear()) },
                    species = speciesDeferred.await().map { LinksData(it.name, it.url.toSwapiSchema()) },
                    starships = starshipsDeferred.await().map { LinksData(it.name, it.url.toSwapiSchema()) },
                    vehicles = vehiclesDeferred.await().map { LinksData(it.name, it.url.toSwapiSchema()) }
                )
            }

        }
    }

}