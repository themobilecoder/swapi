package com.rafaelds.swapi.data.api.vehicles

import com.rafaelds.swapi.data.model.vehicles.VehiclesDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.serialization.KSerializer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VehiclesRemoteService @Inject constructor(networkRequestHelper: NetworkRequestHelper) : RemoteService<VehiclesDTO>(networkRequestHelper) {

    override val outputSerializer: KSerializer<VehiclesDTO>
        get() = VehiclesDTO.serializer()

}