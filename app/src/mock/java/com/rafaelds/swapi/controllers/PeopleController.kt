package com.rafaelds.swapi.controllers

import android.content.Context
import com.rafaelds.swapi.R

class PeopleController(context: Context) : BaseController(context) {
    override fun handleUrl(url: String): String? {
        return when {
            url.matches("/api/people/$".toRegex()) -> {
                Thread.sleep(1000)
                readFromJson(R.raw.people_page)
            }
            url.matches("/api/people/\\d+.*".toRegex()) -> {
                readFromJson(R.raw.person_page)
            }
            else -> {
                null
            }
        }
    }

}