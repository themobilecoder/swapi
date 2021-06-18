package com.rafaelds.swapi.data.network

class NetworkConfigImpl : NetworkConfig {
    override val baseUri: String
        get() = "http://localhost:8000/api/"
}