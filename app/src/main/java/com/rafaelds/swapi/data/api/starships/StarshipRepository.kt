package com.rafaelds.swapi.data.api.starships

import com.rafaelds.swapi.data.api.ApiStringUtils.extractYear
import com.rafaelds.swapi.data.api.ApiStringUtils.toSwapiSchema
import com.rafaelds.swapi.data.api.films.FilmRemoteService
import com.rafaelds.swapi.data.api.people.PersonRemoteService
import com.rafaelds.swapi.data.model.LinksData
import com.rafaelds.swapi.data.model.starships.Starship
import com.rafaelds.swapi.data.network.NetworkConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StarshipRepository @Inject constructor(
    private val starshipRemoteService: StarshipRemoteService,
    private val personRemoteService: PersonRemoteService,
    private val filmRemoteService: FilmRemoteService,
    private val networkConfig: NetworkConfig,
) {

    private val starshipRepositoryUrl: String
        get() = "${networkConfig.baseUri}starships/"

    suspend fun getStarshipDetails(id: Int): Starship {
        return withContext(Dispatchers.IO) {
            val url = "$starshipRepositoryUrl$id"
            val starshipDTO = starshipRemoteService.fetchData(url)
            val pilotsDeferred = async { starshipDTO.pilots.map { personRemoteService.fetchData(it) } }
            val filmsDeferred = async { starshipDTO.films.map { filmRemoteService.fetchData(it) } }

            coroutineScope {
                Starship(
                    id = id,
                    name = starshipDTO.name,
                    appUri = starshipDTO.url.toSwapiSchema(),
                    cargoCapacity = starshipDTO.cargo_capacity,
                    hyperdriveRating = starshipDTO.hyperdrive_rating,
                    maxAtmoshperingSpeed = starshipDTO.max_atmosphering_speed,
                    crew = starshipDTO.crew,
                    length = starshipDTO.length,
                    consumables = starshipDTO.consumables,
                    model = starshipDTO.model,
                    costInCredits = starshipDTO.cost_in_credits,
                    MGLT = starshipDTO.MGLT,
                    passengers = starshipDTO.passengers,
                    starshipClass = starshipDTO.starship_class,
                    manufacturer = starshipDTO.manufacturer,
                    pilots = pilotsDeferred.await().map { LinksData(it.name, it.url.toSwapiSchema()) },
                    films = filmsDeferred.await().map { LinksData(it.title, it.url.toSwapiSchema(), it.release_date.extractYear()) }
                )
            }

        }
    }

}