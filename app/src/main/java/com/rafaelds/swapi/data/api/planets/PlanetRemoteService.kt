package com.rafaelds.swapi.data.api.planets

import com.rafaelds.swapi.data.model.planets.PlanetDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.serialization.KSerializer
import javax.inject.Inject

class PlanetRemoteService @Inject constructor(networkRequestHelper: NetworkRequestHelper) : RemoteService<PlanetDTO>(networkRequestHelper) {
    override val outputSerializer: KSerializer<PlanetDTO>
        get() = PlanetDTO.serializer()
}