package com.rafaelds.swapi.data.api.films

import com.rafaelds.swapi.data.model.films.FilmDTO
import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.serialization.KSerializer
import javax.inject.Inject

class FilmRemoteService @Inject constructor(networkRequestHelper: NetworkRequestHelper) : RemoteService<FilmDTO>(networkRequestHelper) {
    override val outputSerializer: KSerializer<FilmDTO>
        get() = FilmDTO.serializer()
}