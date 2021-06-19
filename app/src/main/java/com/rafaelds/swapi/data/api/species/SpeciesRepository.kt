package com.rafaelds.swapi.data.api.species

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rafaelds.swapi.data.model.species.Specie
import com.rafaelds.swapi.data.model.species.SpeciesDtoToSpecieListMapper
import com.rafaelds.swapi.data.network.NetworkConfig
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalPagingApi
@Singleton
class SpeciesRepository
@Inject constructor(
    private val speciesRemoteService: SpeciesRemoteService,
    private val networkConfig: NetworkConfig,
    private val speciesDtoToSpecieListMapper: SpeciesDtoToSpecieListMapper
) {

    private val speciesRepositoryUrl: String
        get() = "${networkConfig.baseUri}species/"

    fun getSpecies(): Pager<Int, Specie> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { SpeciesPagingSource(speciesRepositoryUrl, speciesRemoteService, speciesDtoToSpecieListMapper) }
        )
    }


}