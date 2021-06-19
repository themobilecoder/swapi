package com.rafaelds.swapi.data.api.people

import com.rafaelds.swapi.data.api.ApiUtils.toSwapiSchema
import com.rafaelds.swapi.data.api.planets.PlanetRemoteService
import com.rafaelds.swapi.data.model.people.Person
import com.rafaelds.swapi.data.model.planets.PlanetData
import com.rafaelds.swapi.data.network.NetworkConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepository @Inject constructor(
    private val personRemoteService: PersonRemoteService,
    private val planetRemoteService: PlanetRemoteService,
    private val networkConfig: NetworkConfig,
) {

    private val peopleRepositoryUrl: String
        get() = "${networkConfig.baseUri}people/"

    suspend fun getPersonDetails(id: Int): Person {
        return withContext(Dispatchers.IO) {
            val url = "$peopleRepositoryUrl$id"
            val personDTO = personRemoteService.fetchData(url)
            val homeUrl = personDTO.homeworld
            val planetDTO = homeUrl?.let { planetRemoteService.fetchData(homeUrl) }
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
                planetData = planetDTO?.let { PlanetData(name = planetDTO.name, url = planetDTO.url.toSwapiSchema()) },
                films = listOf(),
                species = listOf(),
                starships = listOf(),
                vehicles = listOf()
            )
        }
    }

}