package com.rafaelds.swapi.controllers

import android.content.Context
import com.rafaelds.swapi.R

class FilmsController(context: Context) : BaseController(context) {
    override fun handleUrl(url: String): String? {
        return when {
            url.matches("/api/films/$".toRegex()) -> {
                Thread.sleep(1000)
                readFromJson(R.raw.films)
            }
            url.matches("/api/films/\\d+.*".toRegex()) -> {
                readFromJson(R.raw.film)
            }
            else -> {
                null
            }
        }
    }

}