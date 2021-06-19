package com.rafaelds.swapi.data.api.people

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rafaelds.swapi.data.model.people.PeopleDtoToPersonListMapper
import com.rafaelds.swapi.data.model.people.Person
import com.rafaelds.swapi.data.network.NetworkConfig
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalPagingApi
@Singleton
class PeopleRepository
@Inject constructor(
    private val peopleRemoteService: PeopleRemoteService,
    private val networkConfig: NetworkConfig,
    private val peopleDtoToPersonListMapper: PeopleDtoToPersonListMapper
) {
    fun getPeopleList(): Pager<Int, Person> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { PeoplePagingSource(peopleRepositoryUrl, peopleRemoteService, peopleDtoToPersonListMapper) }
        )
    }

    private val peopleRepositoryUrl: String
        get() = "${networkConfig.baseUri}people/"
}