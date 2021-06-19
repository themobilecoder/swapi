package com.rafaelds.swapi.data.api.vehicles

import com.rafaelds.swapi.data.model.vehicles.VehicleDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.serialization.KSerializer
import javax.inject.Inject

class VehicleRemoteService @Inject constructor(networkRequestHelper: NetworkRequestHelper) : RemoteService<VehicleDTO>(networkRequestHelper) {
    override val outputSerializer: KSerializer<VehicleDTO>
        get() = VehicleDTO.serializer()
}