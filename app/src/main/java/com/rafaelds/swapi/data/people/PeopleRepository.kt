package com.rafaelds.swapi.data.people

import com.rafaelds.swapi.data.ViewState
import com.rafaelds.swapi.ui.people.Person
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRepository @Inject constructor(private val peopleRemoteService: PeopleRemoteService) {
    suspend fun getPeopleList(): ViewState<List<Person>> {
        return peopleRemoteService.fetchData()
    }
}