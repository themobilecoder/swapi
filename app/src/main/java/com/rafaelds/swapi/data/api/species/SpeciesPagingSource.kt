package com.rafaelds.swapi.data.api.species

import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.api.BasePagingSource
import com.rafaelds.swapi.data.model.species.Specie
import com.rafaelds.swapi.data.model.species.SpeciesDTO
import com.rafaelds.swapi.data.model.species.SpeciesDtoToSpecieListMapper

@ExperimentalPagingApi
class SpeciesPagingSource constructor(
    uri: String,
    speciesRemoteService: SpeciesRemoteService,
    mapper: SpeciesDtoToSpecieListMapper
) : BasePagingSource<Specie, SpeciesDTO>(uri, speciesRemoteService, mapper) {

    override fun getPreviousKey(pageNumber: Int, response: SpeciesDTO): Int? {
        return if (response.previous != null) pageNumber - 1 else null
    }

    override fun getNextKey(pageNumber: Int, response: SpeciesDTO): Int? {
        return if (response.next != null) pageNumber + 1 else null
    }
}