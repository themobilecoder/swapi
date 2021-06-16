package com.rafaelds.swapi.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.ViewState
import com.rafaelds.swapi.data.people.PeopleRepository
import com.rafaelds.swapi.data.people.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class PersonDetailViewModel @Inject constructor(private val peopleRepository: PeopleRepository) : ViewModel() {

    val screenState: LiveData<ViewState<Person>> get() = _screenState
    private val _screenState = MutableLiveData<ViewState<Person>>(ViewState.idle())


    fun fetchPersonDetails(id: Int) {
        viewModelScope.launch {
            _screenState.postValue(ViewState.loading())
            try {
                val personDetails = peopleRepository.getPersonDetails(id)
                _screenState.postValue(ViewState.success(personDetails))
            } catch (e: Exception) {
                _screenState.postValue(ViewState.error())
            }
        }
    }
}