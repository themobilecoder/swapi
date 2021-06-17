package com.rafaelds.swapi.data.api.people

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.rafaelds.swapi.data.api.BaseRepository
import com.rafaelds.swapi.data.model.people.PeopleDtoToPersonListMapper
import com.rafaelds.swapi.data.model.people.Person
import com.rafaelds.swapi.data.model.people.PersonDtoToPersonMapper
import com.rafaelds.swapi.data.network.NetworkConfig
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
) : BaseRepository {
    fun getPeopleList(): LiveData<PagingData<Person>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { PeoplePagingSource(repositoryUrl, peopleRemoteService, peopleDtoToPersonListMapper) }
        ).liveData
    }

    suspend fun getPersonDetails(id: Int): Person {
        val url = "$repositoryUrl$id"
        return personDtoToPersonMapper.convert(personRemoteService.fetchData(url))
    }

    override val repositoryUrl: String
        get() = "${networkConfig.baseUri}people/"
}