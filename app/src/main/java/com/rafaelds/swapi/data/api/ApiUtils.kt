package com.rafaelds.swapi.data.api

object ApiUtils {
    fun String.toSwapiSchema(): String {
        if (contains("https://")) {
            return replace("https", "swapi")
        } else if (contains("http://")) {
            return replace("http", "swapi")
        }
        return this
    }
}