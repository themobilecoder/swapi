package com.rafaelds.swapi.data.api.starships

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rafaelds.swapi.data.model.starships.Starship
import com.rafaelds.swapi.data.model.starships.StarshipsDtoToStarshipListMapper
import com.rafaelds.swapi.data.network.NetworkConfig
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalPagingApi
@Singleton
class StarshipsRepository
@Inject constructor(
    private val starshipsRemoteService: StarshipsRemoteService,
    private val networkConfig: NetworkConfig,
    private val starshipsDtoToStarshipListMapper: StarshipsDtoToStarshipListMapper
) {

    private val starshipsRepositoryUrl: String
        get() = "${networkConfig.baseUri}starships/"

    fun getStarships(): Pager<Int, Starship> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                StarshipsPagingSource(
                    starshipsRepositoryUrl,
                    starshipsRemoteService,
                    starshipsDtoToStarshipListMapper
                )
            }
        )
    }


}