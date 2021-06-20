package com.rafaelds.swapi.ui.vehicles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.api.vehicles.VehicleRepository
import com.rafaelds.swapi.data.model.ViewState
import com.rafaelds.swapi.data.model.vehicles.Vehicle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class VehicleDetailsViewModel @Inject constructor(private val vehicleRepository: VehicleRepository) : ViewModel() {

    val screenState: LiveData<ViewState<Vehicle>> get() = _screenState
    private val _screenState = MutableLiveData<ViewState<Vehicle>>(ViewState.idle())


    fun fetchDetails(id: Int) {
        viewModelScope.launch {
            _screenState.postValue(ViewState.loading())
            try {
                val details = vehicleRepository.getVehicleDetails(id)
                _screenState.postValue(ViewState.success(details))
            } catch (e: Exception) {
                _screenState.postValue(ViewState.error())
            }
        }
    }
}