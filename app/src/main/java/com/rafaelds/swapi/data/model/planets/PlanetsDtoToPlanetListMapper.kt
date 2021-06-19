package com.rafaelds.swapi.data.model.planets

import com.rafaelds.swapi.data.api.ApiUtils.toSwapiSchema
import com.rafaelds.swapi.data.model.DtoToModelMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlanetsDtoToPlanetListMapper @Inject constructor() : DtoToModelMapper<PlanetsDTO, List<Planet>> {
    override fun convert(dto: PlanetsDTO): List<Planet> {
        return dto.results.map { planet ->
            val regexMatch = FIND_ID_REGEX.toRegex().find(planet.url)
            Planet(
                id = regexMatch?.destructured?.component1()?.toInt() ?: -1,
                name = planet.name,
                appUri = planet.url.toSwapiSchema(),
                rotationPeriod = planet.rotation_period,
                orbitalPeriod = planet.orbital_period,
                diameter = planet.diameter,
                terrain = planet.terrain,
                surfaceWater = planet.surface_water,
                population = planet.population,
                residents = listOf(),
                films = listOf(),
                gravity = planet.gravity
            )
        }
    }

    companion object {
        private const val FIND_ID_REGEX = """(?:planets/)(\d+)"""
    }
}