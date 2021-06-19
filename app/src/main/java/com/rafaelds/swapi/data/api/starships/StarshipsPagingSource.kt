package com.rafaelds.swapi.data.api.starships

import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.api.BasePagingSource
import com.rafaelds.swapi.data.model.starships.Starship
import com.rafaelds.swapi.data.model.starships.StarshipsDTO
import com.rafaelds.swapi.data.model.starships.StarshipsDtoToStarshipListMapper

@ExperimentalPagingApi
class StarshipsPagingSource constructor(
    uri: String,
    starshipsRemoteService: StarshipsRemoteService,
    mapper: StarshipsDtoToStarshipListMapper
) : BasePagingSource<Starship, StarshipsDTO>(uri, starshipsRemoteService, mapper) {

    override fun getPreviousKey(pageNumber: Int, response: StarshipsDTO): Int? {
        return if (response.previous != null) pageNumber - 1 else null
    }

    override fun getNextKey(pageNumber: Int, response: StarshipsDTO): Int? {
        return if (response.next != null) pageNumber + 1 else null
    }
}