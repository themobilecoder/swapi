package com.rafaelds.swapi.data.api.starships

import com.rafaelds.swapi.data.model.starships.StarshipDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.serialization.KSerializer
import javax.inject.Inject

class StarshipRemoteService @Inject constructor(networkRequestHelper: NetworkRequestHelper) : RemoteService<StarshipDTO>(networkRequestHelper) {
    override val outputSerializer: KSerializer<StarshipDTO>
        get() = StarshipDTO.serializer()
}