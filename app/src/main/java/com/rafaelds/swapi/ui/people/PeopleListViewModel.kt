package com.rafaelds.swapi.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rafaelds.swapi.data.DataState
import com.rafaelds.swapi.data.people.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleListViewModel @Inject constructor(private val peopleRepository: PeopleRepository) :
    ViewModel() {

    val screenState: LiveData<DataState<List<People>>> get() = _screenState
    private val _screenState = MutableLiveData<DataState<List<People>>>(DataState.idle())

    fun fetchPeopleList() {
        viewModelScope.launch(Dispatchers.IO) {
            _screenState.postValue(DataState.loading())
            val peopleList = peopleRepository.getPeopleList()
            _screenState.postValue(peopleList)

        }
    }

}