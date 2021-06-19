package com.rafaelds.swapi.data.model.starships

import com.rafaelds.swapi.data.api.ApiStringUtils.toSwapiSchema
import com.rafaelds.swapi.data.model.DtoToModelMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StarshipsDtoToStarshipListMapper @Inject constructor() : DtoToModelMapper<StarshipsDTO, List<Starship>> {
    override fun convert(dto: StarshipsDTO): List<Starship> {
        return dto.results.map { starship ->
            val regexMatch = FIND_ID_REGEX.toRegex().find(starship.url)
            with(starship) {
                Starship(
                    id = regexMatch?.destructured?.component1()?.toInt() ?: -1,
                    name = name,
                    appUri = url.toSwapiSchema(),
                    films = listOf(),
                    cargoCapacity = cargo_capacity,
                    consumables = consumables,
                    costInCredits = cost_in_credits,
                    crew = crew,
                    hyperdriveRating = hyperdrive_rating,
                    length = length,
                    manufacturer = manufacturer,
                    maxAtmoshperingSpeed = max_atmosphering_speed,
                    MGLT = MGLT,
                    model = model,
                    passengers = passengers,
                    pilots = listOf(),
                    starshipClass = starship_class
                )
            }
        }
    }

    companion object {
        private const val FIND_ID_REGEX = """(?:starships/)(\d+)"""
    }
}