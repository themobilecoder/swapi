package com.rafaelds.swapi.ui

import java.util.*

object ExtensionUtil {
    fun String.safeCapitalize() : String {
        return replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
}