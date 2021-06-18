package com.rafaelds.swapi.data.network

class NetworkConfigImpl : NetworkConfig {
    override val baseUri: String
        get() = "https://swapi.dev/api/"
}