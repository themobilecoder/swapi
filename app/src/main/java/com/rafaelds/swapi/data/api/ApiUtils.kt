package com.rafaelds.swapi.data.api

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object ApiUtils {
    fun String.toSwapiSchema(): String {
        if (contains("https://")) {
            return replace("https", "swapi")
        } else if (contains("http://")) {
            return replace("http", "swapi")
        }
        return this
    }

    @SuppressLint("SimpleDateFormat")
    fun String.extractYear(): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val yearFormat = SimpleDateFormat("yyyy")
        return try {
            yearFormat.format(simpleDateFormat.parse(this))
        } catch (e: Exception) {
            this
        }
    }
}