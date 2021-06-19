package com.rafaelds.swapi.ui.species

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.rafaelds.swapi.data.api.species.SpeciesRepository
import com.rafaelds.swapi.data.model.species.Specie
import com.rafaelds.swapi.ui.views.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class SpecieListViewModel @Inject constructor(private val speciesRepository: SpeciesRepository) :
    BaseListViewModel<Specie>() {

    override fun fetchList(): LiveData<PagingData<Specie>> {
        return speciesRepository.getSpecies().liveData.cachedIn(viewModelScope)
    }

}