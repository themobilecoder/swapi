package com.rafaelds.swapi.controllers

import android.content.Context
import com.rafaelds.swapi.R

class StarshipsController(context: Context) : BaseController(context) {
    override fun handleUrl(url: String): String? {
        return when {
            url.matches("/api/starships/$".toRegex()) -> {
                Thread.sleep(1000)
                readFromJson(R.raw.starships)
            }
            url.matches("/api/starships/\\d+.*".toRegex()) -> {
                readFromJson(R.raw.starship)
            }
            else -> {
                null
            }
        }
    }

}