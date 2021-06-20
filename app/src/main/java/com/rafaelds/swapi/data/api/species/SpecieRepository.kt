package com.rafaelds.swapi.data.api.species

import com.rafaelds.swapi.data.api.ApiStringUtils.extractYear
import com.rafaelds.swapi.data.api.ApiStringUtils.toSwapiSchema
import com.rafaelds.swapi.data.api.films.FilmRemoteService
import com.rafaelds.swapi.data.api.people.PersonRemoteService
import com.rafaelds.swapi.data.api.planets.PlanetRemoteService
import com.rafaelds.swapi.data.model.LinksData
import com.rafaelds.swapi.data.model.planets.PlanetData
import com.rafaelds.swapi.data.model.species.Specie
import com.rafaelds.swapi.data.network.NetworkConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpecieRepository @Inject constructor(
    private val personRemoteService: PersonRemoteService,
    private val planetRemoteService: PlanetRemoteService,
    private val filmRemoteService: FilmRemoteService,
    private val specieRemoteService: SpecieRemoteService,
    private val networkConfig: NetworkConfig,
) {

    private val specieRepositoryUrl: String
        get() = "${networkConfig.baseUri}species/"

    suspend fun getSpecieDetails(id: Int): Specie {
        return withContext(Dispatchers.IO) {
            val url = "$specieRepositoryUrl$id"
            val specieDTO = specieRemoteService.fetchData(url)
            val peopleDeferred = async { specieDTO.people.map { personRemoteService.fetchData(it) } }
            val planetDeferred = async { specieDTO.homeworld?.let { planetRemoteService.fetchData(it) } }
            val filmsDeferred = async { specieDTO.films.map { filmRemoteService.fetchData(it) } }

            coroutineScope {
                Specie(
                    id = id,
                    name = specieDTO.name,
                    appUri = specieDTO.url.toSwapiSchema(),
                    skinColors = specieDTO.skin_colors,
                    hairColors = specieDTO.hair_colors,
                    eyeColors = specieDTO.eye_colors,
                    averageLifespan = specieDTO.average_lifespan,
                    classification = specieDTO.classification,
                    designation = specieDTO.designation,
                    averageHeight = specieDTO.average_height,
                    language = specieDTO.language,
                    homeworld = planetDeferred.await()?.let { PlanetData(it.name, it.url.toSwapiSchema()) },
                    people = peopleDeferred.await().map { LinksData(it.name, it.url.toSwapiSchema()) },
                    films = filmsDeferred.await().map { LinksData(it.title, it.url.toSwapiSchema(), it.release_date.extractYear()) }
                )
            }

        }
    }

}