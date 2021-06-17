package com.rafaelds.swapi.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rafaelds.swapi.data.api.people.PeopleRepository
import com.rafaelds.swapi.data.model.people.Person
import com.rafaelds.swapi.ui.views.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class PersonListViewModel @Inject constructor(private val peopleRepository: PeopleRepository) :
    BaseListViewModel<Person>() {
    override fun fetchList(): LiveData<PagingData<Person>> {
        return peopleRepository.getPeopleList().cachedIn(viewModelScope)
    }

}