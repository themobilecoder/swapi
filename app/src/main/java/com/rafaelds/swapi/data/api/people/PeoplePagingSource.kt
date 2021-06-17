package com.rafaelds.swapi.data.api.people

import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.api.BasePagingSource
import com.rafaelds.swapi.data.model.people.PeopleDTO
import com.rafaelds.swapi.data.model.people.PeopleDtoToPersonListMapper
import com.rafaelds.swapi.data.model.people.Person

@ExperimentalPagingApi
class PeoplePagingSource constructor(
    uri: String,
    peopleRemoteService: PeopleRemoteService,
    mapper: PeopleDtoToPersonListMapper
) : BasePagingSource<Person, PeopleDTO>(uri, peopleRemoteService, mapper) {

    override fun getPreviousKey(pageNumber: Int, response: PeopleDTO): Int? {
        return if (response.previous != null) pageNumber - 1 else null
    }

    override fun getNextKey(pageNumber: Int, response: PeopleDTO): Int? {
        return if (response.next != null) pageNumber + 1 else null
    }
}