package com.rafaelds.swapi.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rafaelds.swapi.data.people.Person
import com.rafaelds.swapi.data.people.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class PersonListViewModel @Inject constructor(private val peopleRepository: PeopleRepository) :
    ViewModel() {

    fun fetchPeopleList(): LiveData<PagingData<Person>> {
        return peopleRepository.getPeopleList().cachedIn(viewModelScope)
    }

}