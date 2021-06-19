package com.rafaelds.swapi.ui.planets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.api.planets.PlanetRepository
import com.rafaelds.swapi.data.model.ViewState
import com.rafaelds.swapi.data.model.planets.Planet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class PlanetDetailsViewModel @Inject constructor(private val planetRepository: PlanetRepository) : ViewModel() {

    val screenState: LiveData<ViewState<Planet>> get() = _screenState
    private val _screenState = MutableLiveData<ViewState<Planet>>(ViewState.idle())


    fun fetchPlanetDetails(id: Int) {
        viewModelScope.launch {
            _screenState.postValue(ViewState.loading())
            try {
                val personDetails = planetRepository.getPlanetDetails(id)
                _screenState.postValue(ViewState.success(personDetails))
            } catch (e: Exception) {
                _screenState.postValue(ViewState.error())
            }
        }
    }
}