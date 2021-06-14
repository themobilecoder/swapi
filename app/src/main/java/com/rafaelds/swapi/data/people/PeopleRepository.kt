package com.rafaelds.swapi.data.people

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.rafaelds.swapi.data.network.NetworkConfig
import com.rafaelds.swapi.ui.people.Person
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalPagingApi
@Singleton
class PeopleRepository
@Inject constructor(private val peopleRemoteService: PeopleRemoteService, private val networkConfig: NetworkConfig) {
    fun getPeopleList(): LiveData<PagingData<Person>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { PeoplePagingSource(peopleRemoteService, networkConfig) }
        ).liveData
    }
}