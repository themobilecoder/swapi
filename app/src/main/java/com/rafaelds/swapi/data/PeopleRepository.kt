package com.rafaelds.swapi.data

import com.rafaelds.swapi.ui.people.People
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRepository @Inject constructor() {
    //TODO: Dummy data
    suspend fun getPeopleList(): DataState<List<People>> {
        return withContext(Dispatchers.IO) {
            DataState.loading<List<People>>()
            delay(2000)
            DataState.success(listOf(
                People(1, "Rafael"),
                People(2, "Natasha"),
                People(3, "Luke Skywalker"),
                People(4, "Some guy"),
                People(5, "Ted"),
                People(6, "Leah Skywalker"),
            ))
        }
    }
}