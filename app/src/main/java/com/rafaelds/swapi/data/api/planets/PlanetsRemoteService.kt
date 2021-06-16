package com.rafaelds.swapi.data.api.planets

import com.rafaelds.swapi.data.model.planets.PlanetsDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.serialization.KSerializer
import javax.inject.Inject

class PlanetsRemoteService @Inject constructor(networkRequestHelper: NetworkRequestHelper) : RemoteService<PlanetsDTO>(networkRequestHelper) {
    override val outputSerializer: KSerializer<PlanetsDTO>
        get() = PlanetsDTO.serializer()
}