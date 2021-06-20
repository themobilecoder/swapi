package com.rafaelds.swapi.ui.species

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.api.species.SpecieRepository
import com.rafaelds.swapi.data.model.ViewState
import com.rafaelds.swapi.data.model.species.Specie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class SpecieDetailsViewModel @Inject constructor(private val specieRepository: SpecieRepository) : ViewModel() {

    val screenState: LiveData<ViewState<Specie>> get() = _screenState
    private val _screenState = MutableLiveData<ViewState<Specie>>(ViewState.idle())


    fun fetchDetails(id: Int) {
        viewModelScope.launch {
            _screenState.postValue(ViewState.loading())
            try {
                val details = specieRepository.getSpecieDetails(id)
                _screenState.postValue(ViewState.success(details))
            } catch (e: Exception) {
                _screenState.postValue(ViewState.error())
            }
        }
    }
}