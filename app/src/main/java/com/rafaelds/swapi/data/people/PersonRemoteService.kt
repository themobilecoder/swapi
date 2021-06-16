package com.rafaelds.swapi.data.people

import com.rafaelds.swapi.data.network.NetworkRequestHelper
import com.rafaelds.swapi.data.network.RemoteService
import kotlinx.serialization.KSerializer
import javax.inject.Inject

class PersonRemoteService @Inject constructor(networkRequestHelper: NetworkRequestHelper) : RemoteService<PersonDTO>(networkRequestHelper) {
    override val outputSerializer: KSerializer<PersonDTO>
        get() = PersonDTO.serializer()
}