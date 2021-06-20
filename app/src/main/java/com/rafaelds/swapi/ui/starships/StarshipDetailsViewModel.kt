package com.rafaelds.swapi.ui.starships

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.api.starships.StarshipRepository
import com.rafaelds.swapi.data.model.ViewState
import com.rafaelds.swapi.data.model.starships.Starship
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class StarshipDetailsViewModel @Inject constructor(private val starshipRepository: StarshipRepository) : ViewModel() {

    val screenState: LiveData<ViewState<Starship>> get() = _screenState
    private val _screenState = MutableLiveData<ViewState<Starship>>(ViewState.idle())


    fun fetchDetails(id: Int) {
        viewModelScope.launch {
            _screenState.postValue(ViewState.loading())
            try {
                val details = starshipRepository.getStarshipDetails(id)
                _screenState.postValue(ViewState.success(details))
            } catch (e: Exception) {
                _screenState.postValue(ViewState.error())
            }
        }
    }
}