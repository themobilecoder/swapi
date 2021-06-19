package com.rafaelds.swapi.data.model.species

import com.rafaelds.swapi.data.api.ApiUtils.toSwapiSchema
import com.rafaelds.swapi.data.model.DtoToModelMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpeciesDtoToSpecieListMapper @Inject constructor() : DtoToModelMapper<SpeciesDTO, List<Specie>> {
    override fun convert(dto: SpeciesDTO): List<Specie> {
        return dto.results.map { specie ->
            val regexMatch = FIND_ID_REGEX.toRegex().find(specie.url)
            with(specie) {
                Specie(
                    id = regexMatch?.destructured?.component1()?.toInt() ?: -1,
                    name = name,
                    appUri = url.toSwapiSchema(),
                    averageHeight = average_height,
                    averageLifespan = average_lifespan,
                    classification = classification,
                    designation = designation,
                    eyeColors = eye_colors,
                    skinColors = skin_colors,
                    hairColors = hair_colors,
                    language = language
                )
            }
        }
    }

    companion object {
        private const val FIND_ID_REGEX = """(?:species/)(\d+)"""
    }
}