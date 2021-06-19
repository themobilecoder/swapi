package com.rafaelds.swapi.data.model.vehicles

import kotlinx.serialization.Serializable

@Serializable
data class VehiclesDTO(val count: Int, val next: String?, val results: List<VehicleDTO>, val previous: String?)