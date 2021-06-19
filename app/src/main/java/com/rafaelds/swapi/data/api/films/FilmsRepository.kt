package com.rafaelds.swapi.data.api.films

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rafaelds.swapi.data.model.films.Film
import com.rafaelds.swapi.data.model.films.FilmsDtoToFilmListMapper
import com.rafaelds.swapi.data.network.NetworkConfig
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalPagingApi
@Singleton
class FilmsRepository
@Inject constructor(
    private val filmsRemoteService: FilmsRemoteService,
    private val networkConfig: NetworkConfig,
    private val filmsDtoToFilmListMapper: FilmsDtoToFilmListMapper
) {

    private val peopleRepositoryUrl: String
        get() = "${networkConfig.baseUri}films/"

    fun getFilms(): Pager<Int, Film> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { FilmsPagingSource(peopleRepositoryUrl, filmsRemoteService, filmsDtoToFilmListMapper) }
        )
    }


}