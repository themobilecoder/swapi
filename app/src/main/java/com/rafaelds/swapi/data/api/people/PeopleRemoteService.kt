package com.rafaelds.swapi.data.api.people

import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import com.rafaelds.swapi.data.model.people.PeopleDTO
import kotlinx.serialization.KSerializer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRemoteService @Inject constructor(networkRequestHelper: NetworkRequestHelper) : RemoteService<PeopleDTO>(networkRequestHelper) {

    override val outputSerializer: KSerializer<PeopleDTO>
        get() = PeopleDTO.serializer()

}