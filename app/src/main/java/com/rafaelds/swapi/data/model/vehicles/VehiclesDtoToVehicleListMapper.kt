package com.rafaelds.swapi.data.model.vehicles

import com.rafaelds.swapi.data.api.ApiUtils.toSwapiSchema
import com.rafaelds.swapi.data.model.DtoToModelMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehiclesDtoToVehicleListMapper @Inject constructor() : DtoToModelMapper<VehiclesDTO, List<Vehicle>> {
    override fun convert(dto: VehiclesDTO): List<Vehicle> {
        return dto.results.map { vehicle ->
            val regexMatch = FIND_ID_REGEX.toRegex().find(vehicle.url)
            with(vehicle) {
                Vehicle(
                    id = regexMatch?.destructured?.component1()?.toInt() ?: -1,
                    name = name,
                    appUri = url.toSwapiSchema(),
                    cargoCapacity = cargo_capacity,
                    consumables = consumables,
                    costInCredits = cost_in_credits,
                    crew = crew,
                    length = length,
                    manufacturer = manufacturer,
                    maxAtmoshperingSpeed = max_atmosphering_speed,
                    model = model,
                    passengers = passengers,
                    vehicleClass = vehicle_class
                )
            }
        }
    }

    companion object {
        private const val FIND_ID_REGEX = """(?:vehicles/)(\d+)"""
    }
}