package com.rafaelds.swapi.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.api.people.PersonRepository
import com.rafaelds.swapi.data.model.ViewState
import com.rafaelds.swapi.data.model.people.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class PersonDetailViewModel @Inject constructor(private val personRepository: PersonRepository) : ViewModel() {

    val screenState: LiveData<ViewState<Person>> get() = _screenState
    private val _screenState = MutableLiveData<ViewState<Person>>(ViewState.idle())


    fun fetchPersonDetails(id: Int) {
        viewModelScope.launch {
            _screenState.postValue(ViewState.loading())
            try {
                val personDetails = personRepository.getPersonDetails(id)
                _screenState.postValue(ViewState.success(personDetails))
            } catch (e: Exception) {
                _screenState.postValue(ViewState.error())
            }
        }
    }
}