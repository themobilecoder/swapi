package com.rafaelds.swapi.data.api.species

import com.rafaelds.swapi.data.model.species.SpeciesDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.serialization.KSerializer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpeciesRemoteService @Inject constructor(networkRequestHelper: NetworkRequestHelper) :
    RemoteService<SpeciesDTO>(networkRequestHelper) {

    override val outputSerializer: KSerializer<SpeciesDTO>
        get() = SpeciesDTO.serializer()

}