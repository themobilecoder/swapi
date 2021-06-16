package com.rafaelds.swapi.data.api.people

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.rafaelds.swapi.data.network.NetworkConfig
import com.rafaelds.swapi.data.model.people.PeopleDtoToPersonListMapper
import com.rafaelds.swapi.data.model.people.Person
import com.rafaelds.swapi.data.model.people.PersonDtoToPersonMapper
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalPagingApi
@Singleton
class PeopleRepository
@Inject constructor(
    private val peopleRemoteService: PeopleRemoteService,
    private val personRemoteService: PersonRemoteService,
    private val networkConfig: NetworkConfig,
    private val peopleDtoToPersonListMapper: PeopleDtoToPersonListMapper,
    private val personDtoToPersonMapper: PersonDtoToPersonMapper
) {
    fun getPeopleList(): LiveData<PagingData<Person>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { PeoplePagingSource(peopleRemoteService, networkConfig, peopleDtoToPersonListMapper) }
        ).liveData
    }

    suspend fun getPersonDetails(id: Int): Person {
        val url = "${networkConfig.baseUri}$PEOPLE_SUB_URI$id/"
        return personDtoToPersonMapper.convert(personRemoteService.fetchData(url))
    }

    companion object {
        private const val PEOPLE_SUB_URI = "people/"
    }
}