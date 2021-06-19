package com.rafaelds.swapi.data.api.films

import com.rafaelds.swapi.data.model.films.FilmsDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.serialization.KSerializer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilmsRemoteService @Inject constructor(networkRequestHelper: NetworkRequestHelper) : RemoteService<FilmsDTO>(networkRequestHelper) {

    override val outputSerializer: KSerializer<FilmsDTO>
        get() = FilmsDTO.serializer()

}