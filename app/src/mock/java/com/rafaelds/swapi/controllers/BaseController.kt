package com.rafaelds.swapi.controllers

import android.content.Context
import java.io.IOException
import kotlin.jvm.Throws

abstract class BaseController(private val context: Context) {

    @Throws(IOException::class)
    abstract fun handleUrl(url: String) : String?

    fun readFromJson(fileResource: Int): String {
        return context.resources.openRawResource(fileResource)
            .bufferedReader().use { it.readText() }
    }
}