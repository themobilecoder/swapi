package com.rafaelds.swapi.ui

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment
import java.util.*

object ExtensionUtil {
    fun String.safeCapitalize() : String {
        return replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    fun Fragment.startActivityWithLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}