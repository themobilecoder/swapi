package com.rafaelds.swapi.data.api.films

import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.api.BasePagingSource
import com.rafaelds.swapi.data.model.films.Film
import com.rafaelds.swapi.data.model.films.FilmsDTO
import com.rafaelds.swapi.data.model.films.FilmsDtoToFilmListMapper

@ExperimentalPagingApi
class FilmsPagingSource constructor(
    uri: String,
    filmsRemoteService: FilmsRemoteService,
    mapper: FilmsDtoToFilmListMapper
) : BasePagingSource<Film, FilmsDTO>(uri, filmsRemoteService, mapper) {

    override fun getPreviousKey(pageNumber: Int, response: FilmsDTO): Int? {
        return if (response.previous != null) pageNumber - 1 else null
    }

    override fun getNextKey(pageNumber: Int, response: FilmsDTO): Int? {
        return if (response.next != null) pageNumber + 1 else null
    }
}