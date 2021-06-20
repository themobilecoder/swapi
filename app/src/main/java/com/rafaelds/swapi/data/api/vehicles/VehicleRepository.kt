package com.rafaelds.swapi.data.api.vehicles

import com.rafaelds.swapi.data.api.ApiStringUtils.extractYear
import com.rafaelds.swapi.data.api.ApiStringUtils.toSwapiSchema
import com.rafaelds.swapi.data.api.films.FilmRemoteService
import com.rafaelds.swapi.data.api.people.PersonRemoteService
import com.rafaelds.swapi.data.model.LinksData
import com.rafaelds.swapi.data.model.vehicles.Vehicle
import com.rafaelds.swapi.data.network.NetworkConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehicleRepository @Inject constructor(
    private val vehicleRemoteService: VehicleRemoteService,
    private val personRemoteService: PersonRemoteService,
    private val filmRemoteService: FilmRemoteService,
    private val networkConfig: NetworkConfig,
) {

    private val vehiclesRepositoryUrl: String
        get() = "${networkConfig.baseUri}vehicles/"

    suspend fun getVehicleDetails(id: Int): Vehicle {
        return withContext(Dispatchers.IO) {
            val url = "$vehiclesRepositoryUrl$id"
            val vehicleDTO = vehicleRemoteService.fetchData(url)
            val pilotsDeferred = async { vehicleDTO.pilots.map { personRemoteService.fetchData(it) } }
            val filmsDeferred = async { vehicleDTO.films.map { filmRemoteService.fetchData(it) } }

            coroutineScope {
                Vehicle(
                    id = id,
                    name = vehicleDTO.name,
                    appUri = vehicleDTO.url.toSwapiSchema(),
                    vehicleClass = vehicleDTO.vehicle_class,
                    cargoCapacity = vehicleDTO.cargo_capacity,
                    maxAtmoshperingSpeed = vehicleDTO.max_atmosphering_speed,
                    crew = vehicleDTO.crew,
                    length = vehicleDTO.length,
                    consumables = vehicleDTO.consumables,
                    model = vehicleDTO.model,
                    costInCredits = vehicleDTO.cost_in_credits,
                    passengers = vehicleDTO.passengers,
                    manufacturer = vehicleDTO.manufacturer,
                    pilots = pilotsDeferred.await().map { LinksData(it.name, it.url.toSwapiSchema()) },
                    films = filmsDeferred.await().map { LinksData(it.title, it.url.toSwapiSchema(), it.release_date.extractYear()) },
                )
            }

        }
    }

}