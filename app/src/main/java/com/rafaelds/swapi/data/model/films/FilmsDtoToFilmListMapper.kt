package com.rafaelds.swapi.data.model.films

import com.rafaelds.swapi.data.api.ApiUtils.toSwapiSchema
import com.rafaelds.swapi.data.model.DtoToModelMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilmsDtoToFilmListMapper @Inject constructor() : DtoToModelMapper<FilmsDTO, List<Film>> {
    override fun convert(dto: FilmsDTO): List<Film> {
        return dto.results.map { film ->
            val regexMatch = FIND_ID_REGEX.toRegex().find(film.url)
            Film(
                id = regexMatch?.destructured?.component1()?.toInt() ?: -1,
                title = film.title,
                appUri = film.url.toSwapiSchema(),
                director = film.director,
                producer = film.producer,
                episode = film.episode_id.toString(),
                releaseDate = film.release_date,
                species = listOf(),
                starships = listOf(),
                vehicles = listOf(),
                characters = listOf(),
                planets = listOf()
            )
        }
    }

    companion object {
        private const val FIND_ID_REGEX = """(?:films/)(\d+)"""
    }
}