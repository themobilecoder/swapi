package com.rafaelds.swapi.data.api.people

import androidx.lifecycle.LiveData
import androidx.paging.*
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
    fun getPeopleList(): LiveData<PagingData<Person>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { PeoplePagingSource(peopleRepositoryUrl, peopleRemoteService, peopleDtoToPersonListMapper) }
        ).liveData
    }

    private val peopleRepositoryUrl: String
        get() = "${networkConfig.baseUri}people/"
}