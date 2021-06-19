package com.rafaelds.swapi.data.api.starships

import com.rafaelds.swapi.data.model.starships.StarshipsDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.serialization.KSerializer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StarshipsRemoteService @Inject constructor(networkRequestHelper: NetworkRequestHelper) : RemoteService<StarshipsDTO>(networkRequestHelper) {

    override val outputSerializer: KSerializer<StarshipsDTO>
        get() = StarshipsDTO.serializer()

}