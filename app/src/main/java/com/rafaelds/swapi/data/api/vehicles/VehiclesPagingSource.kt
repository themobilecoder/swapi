package com.rafaelds.swapi.data.api.vehicles

import androidx.paging.ExperimentalPagingApi
import com.rafaelds.swapi.data.api.BasePagingSource
import com.rafaelds.swapi.data.model.vehicles.Vehicle
import com.rafaelds.swapi.data.model.vehicles.VehiclesDTO
import com.rafaelds.swapi.data.model.vehicles.VehiclesDtoToVehicleListMapper

@ExperimentalPagingApi
class VehiclesPagingSource constructor(
    uri: String,
    vehiclesRemoteService: VehiclesRemoteService,
    mapper: VehiclesDtoToVehicleListMapper
) : BasePagingSource<Vehicle, VehiclesDTO>(uri, vehiclesRemoteService, mapper) {

    override fun getPreviousKey(pageNumber: Int, response: VehiclesDTO): Int? {
        return if (response.previous != null) pageNumber - 1 else null
    }

    override fun getNextKey(pageNumber: Int, response: VehiclesDTO): Int? {
        return if (response.next != null) pageNumber + 1 else null
    }
}