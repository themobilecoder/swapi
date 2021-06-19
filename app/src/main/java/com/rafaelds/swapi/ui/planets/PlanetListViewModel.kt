package com.rafaelds.swapi.ui.planets

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.rafaelds.swapi.data.api.planets.PlanetsRepository
import com.rafaelds.swapi.data.model.planets.Planet
import com.rafaelds.swapi.ui.views.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class PlanetListViewModel @Inject constructor(private val planetsRepository: PlanetsRepository) :
    BaseListViewModel<Planet>() {

    override fun fetchList(): LiveData<PagingData<Planet>> {
        return planetsRepository.getPlanetList().liveData.cachedIn(viewModelScope)
    }

}