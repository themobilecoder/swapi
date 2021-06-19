package com.rafaelds.swapi.data.api.species

import com.rafaelds.swapi.data.model.species.SpecieDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.serialization.KSerializer
import javax.inject.Inject

class SpecieRemoteService @Inject constructor(networkRequestHelper: NetworkRequestHelper) : RemoteService<SpecieDTO>(networkRequestHelper) {
    override val outputSerializer: KSerializer<SpecieDTO>
        get() = SpecieDTO.serializer()
}