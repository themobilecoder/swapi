package com.rafaelds.swapi.data.api.people

import com.rafaelds.swapi.data.api.ApiUtils.toSwapiSchema
import com.rafaelds.swapi.data.api.planets.PlanetRemoteService
import com.rafaelds.swapi.data.model.people.HomeData
import com.rafaelds.swapi.data.model.people.Person
import com.rafaelds.swapi.data.network.NetworkConfig
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
        val url = "$peopleRepositoryUrl$id"
        val personDTO = personRemoteService.fetchData(url)
        val homeUrl = personDTO.homeworld
        val planetDTO = planetRemoteService.fetchData(homeUrl)
        return Person(
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
            homeData = HomeData(name = planetDTO.name, url = planetDTO.url.toSwapiSchema())
        )
    }
}