package com.rafaelds.swapi.ui.vehicles

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.rafaelds.swapi.data.api.vehicles.VehiclesRepository
import com.rafaelds.swapi.data.model.vehicles.Vehicle
import com.rafaelds.swapi.ui.views.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class VehicleListViewModel @Inject constructor(private val vehiclesRepository: VehiclesRepository) :
    BaseListViewModel<Vehicle>() {

    override fun fetchList(): LiveData<PagingData<Vehicle>> {
        return vehiclesRepository.getStarships().liveData.cachedIn(viewModelScope)
    }

}