package com.rafaelds.swapi.controllers

import android.content.Context
import com.rafaelds.swapi.R

class StarshipsController(context: Context) : BaseController(context) {
    override fun handleUrl(url: String): String? {
        return when {
            url.matches("/api/vehicles/$".toRegex()) -> {
                Thread.sleep(1000)
                readFromJson(R.raw.vehicles)
            }
            url.matches("/api/vehicles/\\d+.*".toRegex()) -> {
                readFromJson(R.raw.vehicle)
            }
            else -> {
                null
            }
        }
    }

}