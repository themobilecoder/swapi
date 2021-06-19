package com.rafaelds.swapi.data.api.vehicles

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rafaelds.swapi.data.model.vehicles.Vehicle
import com.rafaelds.swapi.data.model.vehicles.VehiclesDtoToVehicleListMapper
import com.rafaelds.swapi.data.network.NetworkConfig
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalPagingApi
@Singleton
class VehiclesRepository
@Inject constructor(
    private val vehiclesRemoteService: VehiclesRemoteService,
    private val networkConfig: NetworkConfig,
    private val vehiclesDtoToVehicleListMapper: VehiclesDtoToVehicleListMapper
) {

    private val vehiclesRepositoryUrl: String
        get() = "${networkConfig.baseUri}vehicles/"

    fun getStarships(): Pager<Int, Vehicle> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                VehiclesPagingSource(
                    vehiclesRepositoryUrl,
                    vehiclesRemoteService,
                    vehiclesDtoToVehicleListMapper
                )
            }
        )
    }


}